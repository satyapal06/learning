object EitherLeftRight {
  def divideXByY(x: Int, y: Int): Either[String, Int] = {
    if (y == 0) Left("Dude, can't divide by 0")
    else Right(x / y)
  }

  def main(args: Array[String]): Unit = {
    val x = divideXByY(1, 1).right.getOrElse(0) // returns 1
    val y = divideXByY(1, 0).right.getOrElse(0) // returns 0

    // prints "Answer: Dude, can't divide by 0"
    divideXByY(1, 0) match {
      case Left(s) => println("Answer: " + s)
      case Right(i) => println("Answer: " + i)
    }

    val x1 = divideXByY(1, 0)
    if(x1.isLeft) x1.left
  }
}
