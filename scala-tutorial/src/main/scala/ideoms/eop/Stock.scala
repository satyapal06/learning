package ideoms.eop

// an intentionally bad example
class Stock (var symbol: String,
             var company: String,
             var price: String,
             var volume: String,
             var high: String,
             var low: String) {
  var html: String = _
  def buildUrl(stockSymbol: String): String = { ??? }
  def getUrlContent(url: String):String = { ??? }
  def setPriceUsingHtml() { this.price = ??? }
  def setVolumeUsingHtml() { this.volume = ??? }
  def setHighUsingHtml() { this.high = ??? }
  def setLowUsingHtml() { this.low = ??? }
}

object Test {
  def main(args: Array[String]): Unit = {
    val stock = new Stock("GOOG", "Google", "", "", "", "")
    val url = stock.buildUrl(stock.symbol)
    stock.html = stock.getUrlContent(url)
    // a series of calls on an object ('statements')
    stock.setPriceUsingHtml
    stock.setVolumeUsingHtml
    stock.setHighUsingHtml
    stock.setLowUsingHtml
  }
}