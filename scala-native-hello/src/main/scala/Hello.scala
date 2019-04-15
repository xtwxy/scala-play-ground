import reactify._

object Hello extends App {
  println("Hello, World!")
  val myChannel = Channel[String]           // Creates a Channel that receives Strings
  val myVar = Var[Int](5)                   // Creates a Var containing the explicit value `5`
  val myVal = Val[Int](myVar + 5)           // Create a Val containing the sum of `myVar` + `5`
  myVal.attach { newValue =>
    println(s"myVal = $newValue")
  }
}
