package ideoms.eop.solution

import java.util.Calendar

case class StockUtilInstance(symbol: String, date: Calendar, price: String, volume: String, high: String, low: String)

object StockUtil {
  def buildUrl(symbol: String): String = { ??? }
  def getPrice(symbol: String, html: String): String = { ??? }
  def getVolume(symbol: String, html: String): String = { ??? }
  def getHigh(symbol: String, html: String): String = { ??? }
  def getLow(symbol: String, html: String): String = { ??? }
}

object DateUtils {
  def getDate = Calendar.getInstance()
}

object NetUtils {
  def getUrlContent(url: String): String = ???
}

object Test {
  def main(args: Array[String]): Unit = {
    val symbol = ""
    val url = StockUtil.buildUrl(symbol)
    val html = NetUtils.getUrlContent(url)
    val price = StockUtil.getPrice(symbol, html)
    val volume = StockUtil.getVolume(symbol, html)
    val high = StockUtil.getHigh(symbol, html)
    val low = StockUtil.getLow(symbol, html)
    val date = DateUtils.getDate
    val stockUtilInstance = StockUtilInstance(symbol, date, price, volume, high, low)
  }
}