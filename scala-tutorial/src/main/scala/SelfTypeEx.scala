object SelfTypeEx {
  def main(args: Array[String]): Unit = {
    val service = new Service with ReaderWriter with Serializable
    println(service.reading)

    val service1 = new Service with ReadWriteDB  with Serializable
    println(service1.reading)
  }
}

trait ReaderWriter {
  def read: String = "data from file"
  def write: String = "written to a file"
}

class Service {
  this: ReaderWriter with Serializable =>
  def reading = read(0)
  def writing = write(0)
}

trait ReadWriteDB extends ReaderWriter {
  override def read: String = "data from DB"
  override def write: String = "data written to DB"
}