object PatternMatchingLab {
  def main(args: Array[String]): Unit = {
    println("=" * 50)
    println("Лабораторная работа №3")
    println("Pattern matching и case-классы")
    println("=" * 50)
    
    // Задание 1: Case-классы и pattern matching
    println("\n--- Задание 1: Case-классы и pattern matching ---")
    task1_CaseClassesAndPatternMatching()
    
    // Задание 2: Pattern matching с произвольными типами
    println("\n--- Задание 2: Pattern matching с произвольными типами ---")
    task2_PatternMatchingWithAny()
    
    // Задание 3: Алгебраические типы данных (ADT)
    println("\n--- Задание 3: Алгебраические типы данных (ADT) ---")
    task3_AlgebraicDataTypes()
    
    // Задание 4: Полиморфные функции с дженериками
    println("\n--- Задание 4: Полиморфные функции с дженериками ---")
    task4_PolymorphicFunctions()
  }
  
  // ===== ЗАДАНИЕ 1: Case-классы и pattern matching =====
  
  // Определение case-классов
  case class Person(name: String, age: Int, city: String)
  case class Book(title: String, author: String, pages: Int)
  case class Product(name: String, price: Double, category: String)
  
  def task1_CaseClassesAndPatternMatching(): Unit = {
    // Создание экземпляров case-классов
    val person1 = Person("Анна", 25, "Москва")
    val person2 = Person("Борис", 30, "Санкт-Петербург")
    val person3 = Person("Виктор", 17, "Казань")
    
    val book1 = Book("Scala Programming", "Martin Odersky", 300)
    val book2 = Book("Functional Programming", "John Doe", 250)
    
    val product1 = Product("Laptop", 999.99, "Electronics")
    val product2 = Product("Coffee", 2.50, "Food")
    
    println("Созданные объекты:")
    println(s"Person: $person1")
    println(s"Book: $book1")
    println(s"Product: $product1")
    
    // Pattern matching с case-классами
    def processEntity(entity: Any): String = entity match {
      case Person(name, age, city) if age >= 18 => 
        s"Совершеннолетний: $name из $city"
      case Person(name, age, city) => 
        s"Несовершеннолетний: $name из $city"
      case Book(title, author, pages) if pages > 200 => 
        s"Большая книга: '$title' от $author"
      case Book(title, author, _) => 
        s"Книга: '$title' от $author"
      case Product(name, price, "Electronics") => 
        f"Электроника: $name за $$$price%.2f"
      case Product(name, price, category) => 
        f"$category: $name за $$$price%.2f"
      case _ => "Неизвестный тип объекта"
    }
    
    println("\nPattern matching результаты:")
    val entities = List(person1, person2, person3, book1, book2, product1, product2)
    entities.foreach { entity =>
      println(s"  ${processEntity(entity)}")
    }
    
    // Дополнительные операции с case-классами
    println("\nДополнительные операции:")
    
    // Копирование с изменением
    val person1Older = person1.copy(age = 26)
    println(s"Копия с изменением возраста: $person1Older")
    
    // Автоматически генерируемые методы
    println(s"Равенство объектов: ${person1 == person1.copy()}")
    println(s"Хэш-код person1: ${person1.hashCode()}")
    println(s"Разложение на компоненты: ${person1.productIterator.mkString(", ")}")
  }
  
  // ===== ЗАДАНИЕ 2: Pattern matching с произвольными типами =====
  
  def task2_PatternMatchingWithAny(): Unit = {
    
    // Функция для обработки произвольных типов
    def processAny(value: Any): String = value match {
      // Сопоставление с примитивами
      case x: Int if x > 0 => s"Положительное целое: $x"
      case x: Int if x < 0 => s"Отрицательное целое: $x"
      case 0 => "Ноль"
      
      case x: Double if x > 0.0 => f"Положительное дробное: $x%.2f"
      case x: Double if x < 0.0 => f"Отрицательное дробное: $x%.2f"
      case 0.0 => "Ноль (double)"
      
      case true => "Логическая истина"
      case false => "Логическая ложь"
      
      // Сопоставление со строками
      case s: String if s.length > 10 => s"Длинная строка: ${s.take(10)}..."
      case s: String if s.isEmpty => "Пустая строка"
      case s: String => s"Строка: '$s'"
      
      // Сопоставление с коллекциями
      case list: List[_] if list.isEmpty => "Пустой список"
      case list: List[_] => s"Список из ${list.length} элементов"
      
      case array: Array[_] => s"Массив: ${array.mkString("[", ", ", "]")}"
      
      // Сопоставление с кортежами
      case (a, b) => s"Кортеж из 2 элементов: ($a, $b)"
      case (a, b, c) => s"Кортеж из 3 элементов: ($a, $b, $c)"
      
      // Сопоставление с Option
      case Some(x) => s"Значение Some: $x"
      case None => "Значение None"
      
      // Сопоставление с case-классами из задания 1
      case Person(name, age, _) => s"Человек: $name ($age лет)"
      case Book(title, _, _) => s"Книга: $title"
      
      // Wildcard и guard условия
      case x: Int if x % 2 == 0 => s"Чётное число: $x"
      case x: Int => s"Нечётное число: $x"
      
      // Default case
      case _ => s"Неизвестный тип: ${value.getClass.getSimpleName}"
    }
    
    // Тестирование функции
    val testValues: List[Any] = List(
      42,
      -5,
      0,
      3.14,
      -2.5,
      true,
      false,
      "Hello, World!",
      "",
      "Short",
      List(1, 2, 3, 4, 5),
      List("a", "b", "c"),
      List(),
      Array(1, 2, 3),
      (1, "two"),
      (1, 2, 3),
      Some("value"),
      None,
      Person("Мария", 30, "Москва"),
      Book("Scala", "Author", 100),
      List(1, 2, 3), // специальный случай
      7 // нечётное число
    )
    
    println("Обработка произвольных типов:")
    testValues.foreach { value =>
      println(s"  ${processAny(value)}")
    }
    
    // Дополнительные примеры pattern matching
    println("\nДополнительные примеры:")
    
    // Pattern matching в переменных
    val (x, y) = (10, "hello")
    println(s"Извлечение из кортежа: x=$x, y=$y")
    
    // Pattern matching в for-comprehension
    val people = List(
      Person("Алексей", 25, "Москва"),
      Person("Ольга", 17, "СПб"),
      Person("Иван", 30, "Казань")
    )
    
    val adults = for {
      Person(name, age, city) <- people if age >= 18
    } yield s"$name из $city"
    
    println(s"Совершеннолетние: ${adults.mkString(", ")}")
  }
  
  // ===== ЗАДАНИЕ 3: Алгебраические типы данных (ADT) =====
  
  // Определение ADT для арифметических выражений
  sealed trait ArithmeticExpression
  case class Number(value: Int) extends ArithmeticExpression
  case class Variable(name: String) extends ArithmeticExpression
  case class Add(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
  case class Subtract(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
  case class Multiply(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
  case class Divide(left: ArithmeticExpression, right: ArithmeticExpression) extends ArithmeticExpression
  case class Power(base: ArithmeticExpression, exponent: ArithmeticExpression) extends ArithmeticExpression
  
  def task3_AlgebraicDataTypes(): Unit = {
    
    // Функция вычисления значения выражения
    def evaluate(expression: ArithmeticExpression, variables: Map[String, Int] = Map()): Int = 
      expression match {
        case Number(value) => value
        case Variable(name) => variables.getOrElse(name, 
          throw new IllegalArgumentException(s"Неизвестная переменная: $name"))
        case Add(left, right) => evaluate(left, variables) + evaluate(right, variables)
        case Subtract(left, right) => evaluate(left, variables) - evaluate(right, variables)
        case Multiply(left, right) => evaluate(left, variables) * evaluate(right, variables)
        case Divide(left, right) => 
          val denominator = evaluate(right, variables)
          if (denominator == 0) throw new ArithmeticException("Деление на ноль")
          else evaluate(left, variables) / denominator
        case Power(base, exponent) => 
          math.pow(evaluate(base, variables), evaluate(exponent, variables)).toInt
      }
    
    // Функция для красивого вывода выражения
    def prettyPrint(expression: ArithmeticExpression): String = expression match {
      case Number(value) => value.toString
      case Variable(name) => name
      case Add(left, right) => s"(${prettyPrint(left)} + ${prettyPrint(right)})"
      case Subtract(left, right) => s"(${prettyPrint(left)} - ${prettyPrint(right)})"
      case Multiply(left, right) => s"(${prettyPrint(left)} * ${prettyPrint(right)})"
      case Divide(left, right) => s"(${prettyPrint(left)} / ${prettyPrint(right)})"
      case Power(base, exponent) => s"(${prettyPrint(base)} ^ ${prettyPrint(exponent)})"
    }
    
    // Функция упрощения выражений
    def simplify(expression: ArithmeticExpression): ArithmeticExpression = expression match {
      case Add(Number(0), right) => simplify(right)
      case Add(left, Number(0)) => simplify(left)
      case Multiply(Number(1), right) => simplify(right)
      case Multiply(left, Number(1)) => simplify(left)
      case Multiply(Number(0), _) => Number(0)
      case Multiply(_, Number(0)) => Number(0)
      case Add(Number(a), Number(b)) => Number(a + b)
      case Multiply(Number(a), Number(b)) => Number(a * b)
      case Add(left, right) => Add(simplify(left), simplify(right))
      case Multiply(left, right) => Multiply(simplify(left), simplify(right))
      case other => other
    }
    
    // Создание и тестирование выражений
    val expr1 = Add(Number(5), Multiply(Number(3), Number(2))) // 5 + (3 * 2)
    val expr2 = Subtract(Add(Number(10), Number(5)), Number(3)) // (10 + 5) - 3
    val expr3 = Divide(Multiply(Number(8), Number(2)), Number(4)) // (8 * 2) / 4
    val expr4 = Add(Variable("x"), Multiply(Variable("y"), Number(2))) // x + (y * 2)
    val expr5 = Power(Number(2), Number(3)) // 2^3
    val expr6 = Add(Number(0), Multiply(Number(5), Number(1))) // 0 + (5 * 1)
    
    val variables = Map("x" -> 10, "y" -> 3)
    
    println("Арифметические выражения:")
    val expressions = List(expr1, expr2, expr3, expr4, expr5, expr6)
    
    expressions.foreach { expr =>
      val simplified = simplify(expr)
      println(s"  Выражение: ${prettyPrint(expr)}")
      println(s"  Упрощённое: ${prettyPrint(simplified)}")
      try {
        val result = evaluate(expr, variables)
        println(s"  Результат: $result")
      } catch {
        case e: Exception => println(s"  Ошибка: ${e.getMessage}")
      }
      println()
    }
    
    // Демонстрация exhaustiveness checking
    def checkExpressionType(expr: ArithmeticExpression): String = expr match {
      case Number(_) => "Числовая константа"
      case Variable(_) => "Переменная"
      case Add(_, _) => "Сложение"
      case Subtract(_, _) => "Вычитание"
      case Multiply(_, _) => "Умножение"
      case Divide(_, _) => "Деление"
      case Power(_, _) => "Возведение в степень"
      // Компилятор проверяет, что все case-ы покрыты!
    }
    
    println("Типы выражений:")
    expressions.take(3).foreach { expr =>
      println(s"  ${prettyPrint(expr)} -> ${checkExpressionType(expr)}")
    }
  }
  
  // ===== ЗАДАНИЕ 4: Полиморфные функции с дженериками =====
  
  def task4_PolymorphicFunctions(): Unit = {
    
    // Полиморфная функция для поиска элемента в списке
    def findFirst[A](list: List[A], predicate: A => Boolean): Option[A] = 
      list match {
        case Nil => None
        case head :: tail if predicate(head) => Some(head)
        case _ :: tail => findFirst(tail, predicate)
      }
    
    // Полиморфная функция для фильтрации с преобразованием
    def filterMap[A, B](list: List[A], filter: A => Boolean, map: A => B): List[B] =
      list.collect { case element if filter(element) => map(element) }
    
    // Полиморфная функция для объединения двух списков
    def zipWith[A, B, C](list1: List[A], list2: List[B], combine: (A, B) => C): List[C] =
      (list1, list2) match {
        case (Nil, _) | (_, Nil) => Nil
        case (head1 :: tail1, head2 :: tail2) => 
          combine(head1, head2) :: zipWith(tail1, tail2, combine)
      }
    
    // Полиморфная функция с ограничениями типа
    def maximum[T](list: List[T])(implicit ordering: Ordering[T]): Option[T] =
      list match {
        case Nil => None
        case head :: tail => Some(tail.foldLeft(head)(ordering.max))
      }
    
    // Полиморфная функция для работы с Option
    def map2[A, B, C](optA: Option[A], optB: Option[B])(f: (A, B) => C): Option[C] =
      (optA, optB) match {
        case (Some(a), Some(b)) => Some(f(a, b))
        case _ => None
      }
    
    // Тестирование полиморфных функций
    println("Полиморфные функции:")
    
    // Тестирование findFirst
    val numbers = List(1, 3, 5, 7, 9, 2, 4, 6, 8)
    val strings = List("apple", "banana", "cherry", "date")
    
    // Явно указываем типы для лямбда-функций
    println(s"Первое чётное число: ${findFirst(numbers, (n: Int) => n % 2 == 0)}")
    println(s"Первая строка длиннее 5: ${findFirst(strings, (s: String) => s.length > 5)}")
    
    // Тестирование filterMap
    val filteredNumbers = filterMap(numbers, (n: Int) => n > 5, (n: Int) => n * 2)
    val filteredStrings = filterMap(strings, (s: String) => s.startsWith("c"), (s: String) => s.toUpperCase)
    println(s"Числа > 5 умноженные на 2: $filteredNumbers")
    println(s"Строки на 'c' в верхнем регистре: $filteredStrings")
    
    // Тестирование zipWith
    val list1 = List(1, 2, 3)
    val list2 = List(10, 20, 30)
    val zipped = zipWith(list1, list2, (a: Int, b: Int) => a + b)
    println(s"Сумма элементов: $zipped")
    
    val words1 = List("Hello", "Functional")
    val words2 = List("World", "Programming")
    val combinedWords = zipWith(words1, words2, (a: String, b: String) => a + " " + b)
    println(s"Объединённые строки: $combinedWords")
    
    // Тестирование maximum
    println(s"Максимальное число: ${maximum(numbers)}")
    println(s"Максимальная строка: ${maximum(strings)}")
    
    // Тестирование map2
    val opt1 = Some(5)
    val opt2 = Some(3)
    val opt3: Option[Int] = None
    
    println(s"Сумма Some(5) и Some(3): ${map2(opt1, opt2)((a: Int, b: Int) => a + b)}")
    println(s"Сумма Some(5) и None: ${map2(opt1, opt3)((a: Int, b: Int) => a + b)}")
    
    // Дополнительные полиморфные примеры
    println("\nДополнительные примеры:")
    
    // Полиморфная функция с pattern matching
    def processOption[T](option: Option[T]): String = option match {
      case Some(value) => s"Значение: $value"
      case None => "Значение отсутствует"
    }
    
    println(s"Обработка Option: ${processOption(Some(42))}")
    println(s"Обработка Option: ${processOption(None)}")
    
    // Полиморфная функция для пар
    def swapPair[T, U](pair: (T, U)): (U, T) = pair match {
      case (first, second) => (second, first)
    }
    
    println(s"Обмен пар: ${swapPair((1, "hello"))}")
    
    // Полиморфная функция с type bounds
    def printList[T](list: List[T]): String = list match {
      case Nil => "Пустой список"
      case head :: tail => s"Список: ${list.mkString(", ")}"
    }
    
    println(s"Печать списка чисел: ${printList(List(1, 2, 3))}")
    println(s"Печать списка строк: ${printList(List("a", "b", "c"))}")
  }
}