package ideoms.eop

import java.io.{FileNotFoundException, IOException}

import scala.io.Source

object SomeEOPEx {
  def main(args: Array[String]): Unit = {
    val i = 1
    val month = i match {
      case 1 => "January"
      case 2 => "February"
      // more months here ...
      case 11 => "November"
      case 12 => "December"
      case _ => "Invalid month" // the default, catch-all
    }

    println(month)

    i match {
      case 1 | 3 | 5 | 7 | 9 => println("odd")
      case 2 | 4 | 6 | 8 | 10 => println("even")
    }

    def readTextFile(filename: String): Option[List[String]] = {
      try {
        Some(Source.fromFile(filename).getLines.toList)
      } catch {
        case e: Exception => None
      }
    }

    def otherReadTextFile(filename: String): Option[List[String]] = {
      try {
        Some(Source.fromFile(filename).getLines.toList)
      } catch {
        case ioe: IOException =>
          ioe.printStackTrace()
          None
        case fnf: FileNotFoundException =>
          fnf.printStackTrace()
          None
      }
    }

    def isTrue(a: Any) = a match {
      case 0 | "" => false
      case _ => true
    }

    def getClassAsString(x: Any):String = x match {
      case s: String => "String"
      case i: Int => "Int"
      case l: List[_] => "List"
      case p: Person => "Person"
      case Dog() => "That was a Dog"
      case Parrot(name) => s"That was a Parrot, name = $name"
      case _ => "Unknown"
    }

    val divide: PartialFunction[Int, Int] = {
      case d: Int if d != 0 => 42 / d
    }

    def toInt(s: String): Option[Int] = {
      try {
        Some(s.toInt)
      } catch {
        case e: Exception => None
      }
    }

    toInt("aString") match {
      case Some(i) => println(i)
      case None => println("Error: Could not convert String to Int.")
    }
  }
}

case class Person()

case class Dog()
