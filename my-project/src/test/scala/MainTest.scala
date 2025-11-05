import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite {
  test("greet should return hello message") {
    assert(Main.greet("World") == "Hello, World!")
  }
  
  test("sum should calculate correctly") {
    val numbers = List(1, 2, 3)
    assert(numbers.sum == 6)
  }
}
