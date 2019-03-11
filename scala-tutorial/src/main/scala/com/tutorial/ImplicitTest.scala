package com.tutorial

abstract class Monoid[A] {
  def add(x: A, y: A): A
  def unit: A
}

object ImplicitTest {

  implicit val stringMonoid:Monoid[String] = new Monoid[String] {
    override def add(x: String, y: String): String = x concat y

    override def unit: String = ""
  }

  implicit val intMonoid:Monoid[Int] = new Monoid[Int] {
    override def add(x: Int, y: Int): Int = x + y

    override def unit: Int = 0
  }

  implicit val y = 0

  def sum[A](xs: List[A])(implicit monoid: Monoid[A], y:Int): A = {
    if (xs.isEmpty) monoid.unit
    else monoid.add(xs.head, sum(xs.tail))
  }

  def main(args: Array[String]): Unit = {
    println(sum(List(1, 2, 3)))       // uses intMonoid implicitly
    println(sum(List("a", "b", "c"))) // uses stringMonoid implicitly
  }
}
