object OptionExample {
  def main(args: Array[String]): Unit = {
    val returningOptionMethod = new ReturningOptionMethod
    //Use getOrElse
    val x = returningOptionMethod.toInt("1").getOrElse(0)
    //Use foreach
    returningOptionMethod.toInt("1").foreach{ i =>
      println(s"Got an int: $i")
    }
    //Use a match expression
    returningOptionMethod.toInt("1") match {
      case Some(i) => println(i)
      case None => println("That didn't work.")
    }

    //Using Option with Scala collections
    val bag = List("1", "2", "foo", "3", "bar")
    println(bag.map(returningOptionMethod.toInt))
    println(bag.map(returningOptionMethod.toInt).flatten)
    println(bag.flatMap(returningOptionMethod.toInt))
    //The collect method provides another way to achieve the same result
    println( bag.map(returningOptionMethod.toInt).collect{case Some(i) => i})

    val y = returningOptionMethod.toInt("5").get
    val z = returningOptionMethod.toInt("foo").get
    val w = if (returningOptionMethod.toInt("foo").isDefined) returningOptionMethod.toInt("foo") else 0
  }
}

class ReturningOptionMethod {
  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }
}
