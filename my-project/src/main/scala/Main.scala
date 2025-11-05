object Main {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
    println(s"Running my-project")
    
    // Example functionality
    val numbers = List(1, 2, 3, 4, 5)
    val sum = numbers.sum
    val doubled = numbers.map(_ * 2)
    
    println(s"Sum of numbers: $sum")
    println(s"Doubled numbers: $doubled")
  }
  
  // Example method
  def greet(name: String): String = {
    s"Hello, $name!"
  }
}
