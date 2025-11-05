error id: file:///C:/Users/khaye/Desktop/Scala/lab2/src/test/scala/MainTest.scala:`<none>`.
file:///C:/Users/khaye/Desktop/Scala/lab2/src/test/scala/MainTest.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -CollectionsLab.
	 -scala/Predef.CollectionsLab.
offset: 253
uri: file:///C:/Users/khaye/Desktop/Scala/lab2/src/test/scala/MainTest.scala
text:
```scala
import org.scalatest.funsuite.AnyFunSuite

class CollectionsLabTest extends AnyFunSuite {
  
  test("task1_BasicCollections should demonstrate collection operations") {
    // Тест проверяет, что основные операции работают корректно
    assert(Collectio@@nsLab.task1_BasicCollections != null)
  }
  
  test("task2_HigherOrderFunctions should process collections correctly") {
    val numbers = List(1, 2, 3, 4, 5)
    
    // Проверка map
    assert(numbers.map(_ * 2) == List(2, 4, 6, 8, 10))
    
    // Проверка filter
    assert(numbers.filter(_ % 2 == 0) == List(2, 4))
    
    // Проверка reduce
    assert(numbers.reduce(_ + _) == 15)
  }
  
  test("task3_FunctionAsArgument should accept functions as parameters") {
    def testProcessor(x: Int): Int = x * 3
    def testPredicate(x: Int): Boolean = x > 2
    
    val numbers = List(1, 2, 3)
    
    // Можно передавать функции как параметры
    assert(numbers.map(testProcessor) == List(3, 6, 9))
    assert(numbers.filter(testPredicate) == List(3))
  }
  
  test("task4_Currying should demonstrate curried functions") {
    def curriedAdd(a: Int)(b: Int): Int = a + b
    val addFive = curriedAdd(5) _
    
    assert(curriedAdd(2)(3) == 5)
    assert(addFive(10) == 15)
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.