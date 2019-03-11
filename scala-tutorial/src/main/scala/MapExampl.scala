object MapExampl {
  def main(args: Array[String]): Unit = {
    case class Employee(name: String, age:Int)
    val list: List[Employee] = List(Employee("Satya Pal Singh", 31),
      Employee("Suman Singh", 28),
    Employee("Sakshi Singh", 3))

    val transformedList = list.map(employee => (employee.age, employee))
    println(transformedList)

    val map = transformedList.toMap

    println(map)
  }
}
