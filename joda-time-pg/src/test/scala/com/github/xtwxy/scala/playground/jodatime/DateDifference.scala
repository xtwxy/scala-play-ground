package example

import org.joda.time._
import org.scalatest._

class DateDifference extends FlatSpec with Matchers {
  "The Days object" should "tell days between" in {
      Days.daysBetween(new DateTime("2017-01-01"), new DateTime("2017-06-05")).getDays() / 7 shouldEqual 22
  }
}
