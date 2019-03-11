package ideoms.immutable

case class Topping(name:String, toppingType:Int)
class Pizza {
  private val _toppings = new collection.mutable.ArrayBuffer[Topping]()
  def toppings = _toppings.toList
  def addTopping(t: Topping) { _toppings += t }
  def removeTopping(t: Topping) { _toppings -= t }
}

object  Test {
  def main(args: Array[String]): Unit = {
    val pizza = new Pizza
  }
}