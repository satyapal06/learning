package com

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case class Book(title: String, authors: List[String])

class BookPublisher extends Actor with ActorLogging {

  def receive = {
    case book: Book => {
      log.info(s"Yeah! Publishing a new book: $book")
      context.system.eventStream.publish(book)
    }
  }
}

class BookSubscriber extends Actor with ActorLogging {

  override def preStart = context.system.eventStream.subscribe(self, classOf[Book])

  def receive = {
    case book: Book => {
      Thread.sleep(3000)
      println(s"My name is ${self.path.name} and I have received a new book: $book")
    }
  }
}

object EventStreamApp extends App {
  val system = ActorSystem("EventStreamApp")

  val bookPublisher = system.actorOf(Props[BookPublisher], name = "book-publisher")
  val subscriber1 = system.actorOf(Props[BookSubscriber], name = "subscriber-1")
  val subscriber2 = system.actorOf(Props[BookSubscriber], name = "subscriber-2")

  bookPublisher ! Book(title = "Programming in Scala", authors = List("Martin Odersky", "Lex Spoon",
    "Bill Venners"))
  system.eventStream.unsubscribe(subscriber2, classOf[Book])

  bookPublisher ! Book(title = "Akka Concurrency", authors = List("Derek Wyatt"))
}
