package com.tutorial

object Test {
  def main(args: Array[String]): Unit = {
    val str: String = s"My name is Satya Pal Singh & i am living in Delhi which is residing in India."
    val array:Array[String] = str.split(" ")
    println(array.foreach(println(_)))
    val grouped = array.groupBy(x => x)
    println(grouped)
    val wordCount: Map[String, Int] = grouped.map { case (word, frequency) => (word, frequency.length) }
    println(wordCount)

    println("xyz".capitalizeString())

    var list = List(1, 2, 3, 4)
    list ::= 5
  }

  implicit class StringImprovments(inputString: String) {
    def capitalizeString(): String = inputString.toUpperCase()
  }
}
