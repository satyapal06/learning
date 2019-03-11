import scala.io.Source
import scala.util.{Failure, Success, Try}

object TrySuccessFailure {
  def divideXByY(x: Int, y: Int): Try[Int] = {
    Try(x / y)
  }

  def main(args: Array[String]): Unit = {
    println(divideXByY(1,1))
    println(divideXByY(1,0))

    val x = divideXByY(1, 1).getOrElse(0)
    val y = divideXByY(1, 0).getOrElse(0)
    println(x)
    println(y)

    divideXByY(1, 1).foreach(println)
    divideXByY(1, 0).foreach(println)

    divideXByY(1, 1) match {
      case Success(i) => println(s"Success, value is: $i")
      case Failure(s) => println(s"Failed, message is: $s")
    }

    val z = for {
      a <- Try(x.toInt)
      b <- Try(y.toInt)
    } yield a * b

    val answer = z.getOrElse(0) * 2
    println(answer)

    val x1 = divideXByY(1, 0)
    if(x1.isFailure) x1.failed
  }

  def readTextFile(filename: String): Try[List[String]] = {
    Try(Source.fromFile(filename).getLines.toList)
  }
}
