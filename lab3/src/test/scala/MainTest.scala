import org.scalatest.funsuite.AnyFunSuite

class PatternMatchingLabTest extends AnyFunSuite {
  
  test("Task 1: Case classes should work correctly") {
    case class Person(name: String, age: Int, city: String)
    val person = Person("Test", 25, "City")
    assert(person.name == "Test")
    assert(person.age == 25)
    assert(person.copy(age = 30).age == 30)
  }
  
  test("Task 2: Pattern matching should handle different types") {
    def testMatcher(value: Any): String = value match {
      case x: Int => s"Int: $x"
      case s: String => s"String: $s"
      case _ => "Other"
    }
    
    assert(testMatcher(42) == "Int: 42")
    assert(testMatcher("hello") == "String: hello")
  }
  
  test("Task 3: Arithmetic expressions should evaluate correctly") {
    sealed trait ArithmeticExpression
    case class Number(value: Int) extends ArithmeticExpression
    case class Add(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
    case class Multiply(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
    
    def evaluate(expression: ArithmeticExpression): Int = expression match {
      case Number(value) => value
      case Add(left, right) => evaluate(left) + evaluate(right)
      case Multiply(left, right) => evaluate(left) * evaluate(right)
    }
    
    val expr = Add(Number(2), Multiply(Number(3), Number(4)))
    assert(evaluate(expr) == 14)
    
    val simpleExpr = Add(Number(0), Number(5))
    assert(evaluate(simpleExpr) == 5)
  }
  
  test("Task 4: Polymorphic functions should work with different types") {
    def findFirst[A](list: List[A], pred: A => Boolean): Option[A] = 
      list match {
        case Nil => None
        case head :: tail if pred(head) => Some(head)
        case _ :: tail => findFirst(tail, pred)
      }
    
    val numbers = List(1, 2, 3, 4, 5)
    val strings = List("a", "bb", "ccc")
    
    assert(findFirst(numbers, (n: Int) => n > 3) == Some(4))
    assert(findFirst(strings, (s: String) => s.length > 2) == Some("ccc"))
  }
}