object CollectionsLab {
  def main(args: Array[String]): Unit = {
    println("=" * 50)
    println("Лабораторная работа №2")
    println("Коллекции и функции высшего порядка")
    println("=" * 50)
    
    // Задание 1: Создание коллекций и базовые операции
    println("\n--- Задание 1: Базовые операции с коллекциями ---")
    task1_BasicCollections()
    
    // Задание 2: Map, filter, reduce
    println("\n--- Задание 2: Функции высшего порядка ---")
    task2_HigherOrderFunctions()
    
    // Задание 3: Функция высшего порядка
    println("\n--- Задание 3: Функции как аргументы ---")
    task3_FunctionAsArgument()
    
    // Задание 4: Каррирование
    println("\n--- Задание 4: Каррирование функций ---")
    task4_Currying()
    
    // Задание 5: Мутабельные vs Иммутабельные коллекции
    println("\n--- Задание 5: Сравнение коллекций ---")
    task5_MutableVsImmutable()
  }
  
  // Задание 1: Базовые операции с коллекциями
  def task1_BasicCollections(): Unit = {
    println("Создание коллекций различных типов:")
    
    // List - иммутабельный список
    val numbers: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(s"List[Int]: $numbers")
    
    // Seq - последовательность
    val names: Seq[String] = Seq("Анна", "Борис", "Виктор", "Галина", "Дмитрий")
    println(s"Seq[String]: $names")
    
    // Set - множество (уникальные элементы)
    val uniqueNumbers: Set[Int] = Set(1, 2, 2, 3, 3, 4, 5)
    println(s"Set[Int] (уникальные): $uniqueNumbers")
    
    // Map - словарь (ключ-значение)
    val ageMap: Map[String, Int] = Map(
      "Анна" -> 25,
      "Борис" -> 30,
      "Виктор" -> 35,
      "Галина" -> 28
    )
    println(s"Map[String, Int]: $ageMap")
    
    // Vector - эффективная индексируемая коллекция
    val vectorData: Vector[Double] = Vector(1.5, 2.7, 3.1, 4.9)
    println(s"Vector[Double]: $vectorData")
    
    // Базовые операции
    println("\nБазовые операции:")
    println(s"Первый элемент списка: ${numbers.head}")
    println(s"Последний элемент списка: ${numbers.last}")
    println(s"Хвост списка: ${numbers.tail}")
    println(s"Длина списка: ${numbers.length}")
    println(s"Содержит ли список 5? ${numbers.contains(5)}")
    println(s"Сумма всех элементов: ${numbers.sum}")
    println(s"Минимальный элемент: ${numbers.min}")
    println(s"Максимальный элемент: ${numbers.max}")
    
    // Добавление элементов (создание новой коллекции)
    val newNumbers = numbers :+ 11 :+ 12
    println(s"Новый список после добавления: $newNumbers")
  }
  
  // Задание 2: Map, filter, reduce
  def task2_HigherOrderFunctions(): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words = List("функциональное", "программирование", "scala", "коллекции", "функции")
    
    println("Исходные данные:")
    println(s"Числа: $numbers")
    println(s"Слова: $words")
    
    // MAP - преобразование каждого элемента
    println("\n--- MAP (преобразование) ---")
    val squared = numbers.map(x => x * x)
    println(s"Квадраты чисел: $squared")
    
    val lengths = words.map(_.length)
    println(s"Длины слов: $lengths")
    
    val upperCase = words.map(_.toUpperCase)
    println(s"Слова в верхнем регистре: $upperCase")
    
    // FILTER - фильтрация элементов
    println("\n--- FILTER (фильтрация) ---")
    val evenNumbers = numbers.filter(_ % 2 == 0)
    println(s"Чётные числа: $evenNumbers")
    
    val longWords = words.filter(_.length > 8)
    println(s"Длинные слова (>8 символов): $longWords")
    
    val divisibleBy3 = numbers.filter(n => n % 3 == 0)
    println(s"Числа, делящиеся на 3: $divisibleBy3")
    
    // REDUCE - агрегация элементов
    println("\n--- REDUCE (агрегация) ---")
    val sum = numbers.reduce(_ + _)
    println(s"Сумма всех чисел: $sum")
    
    val product = numbers.reduce(_ * _)
    println(s"Произведение всех чисел: $product")
    
    val maxNumber = numbers.reduce((a, b) => if (a > b) a else b)
    println(s"Максимальное число: $maxNumber")
    
    // Комбинации функций
    println("\n--- Комбинации функций ---")
    val result = numbers
      .filter(_ % 2 != 0)    // нечётные числа
      .map(_ * 3)            // умножить на 3
      .reduce(_ + _)         // сумма
    
    println(s"Сумма нечётных чисел, умноженных на 3: $result")
    
    // Работа со строками
    val sentence = words
      .filter(_.length > 5)
      .map(_.capitalize)
      .reduce(_ + " " + _)
    
    println(s"Отфильтрованные и форматированные слова: $sentence")
  }
  
  // Задание 3: Функция высшего порядка
  def task3_FunctionAsArgument(): Unit = {
    println("Функции высшего порядка:")
    
    // Функция, принимающая другую функцию как аргумент
    def processNumbers(numbers: List[Int], processor: Int => Int): List[Int] = {
      numbers.map(processor)
    }
    
    def filterNumbers(numbers: List[Int], predicate: Int => Boolean): List[Int] = {
      numbers.filter(predicate)
    }
    
    def aggregateNumbers(numbers: List[Int], aggregator: (Int, Int) => Int): Int = {
      numbers.reduce(aggregator)
    }
    
    val data = List(1, 2, 3, 4, 5)
    
    // Использование функций высшего порядка
    val doubled = processNumbers(data, x => x * 2)
    println(s"Удвоенные числа: $doubled")
    
    val squares = processNumbers(data, x => x * x)
    println(s"Квадраты чисел: $squares")
    
    val evens = filterNumbers(data, _ % 2 == 0)
    println(s"Чётные числа: $evens")
    
    val sum = aggregateNumbers(data, _ + _)
    println(s"Сумма: $sum")
    
    // Более сложный пример
    def complexOperation(numbers: List[Int], 
                        filterFunc: Int => Boolean,
                        mapFunc: Int => Int,
                        reduceFunc: (Int, Int) => Int): Int = {
      numbers
        .filter(filterFunc)
        .map(mapFunc)
        .reduce(reduceFunc)
    }
    
    val complexResult = complexOperation(
      numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
      filterFunc = n => n % 2 == 0,           // только чётные
      mapFunc = n => n * n,                   // возвести в квадрат
      reduceFunc = (a, b) => a + b            // сложить
    )
    
    println(s"Сумма квадратов чётных чисел: $complexResult")
  }
  
  // Задание 4: Каррирование функций
  def task4_Currying(): Unit = {
    println("Каррирование функций:")
    
    // Обычная функция с несколькими параметрами
    def normalAdd(a: Int, b: Int, c: Int): Int = a + b + c
    
    // Каррированная версия
    def curriedAdd(a: Int)(b: Int)(c: Int): Int = a + b + c
    
    // Частичное применение каррированной функции
    val addFive = curriedAdd(5) _
    val addFiveAndThree = addFive(3)
    val finalResult = addFiveAndThree(2)
    
    println(s"Обычная функция: normalAdd(5, 3, 2) = ${normalAdd(5, 3, 2)}")
    println(s"Каррированная функция: curriedAdd(5)(3)(2) = ${curriedAdd(5)(3)(2)}")
    println(s"Частичное применение: addFive(3)(2) = $finalResult")
    
    // Практический пример: конфигурируемый фильтр
    def createFilter(minValue: Int)(maxValue: Int)(number: Int): Boolean = {
      number >= minValue && number <= maxValue
    }
    
    val filterBetween1and5 = createFilter(1)(5) _
    val filterBetween10and20 = createFilter(10)(20) _
    
    val testNumbers = List(1, 3, 7, 15, 25)
    
    val inRange1to5 = testNumbers.filter(filterBetween1and5)
    val inRange10to20 = testNumbers.filter(filterBetween10and20)
    
    println(s"Числа в диапазоне 1-5: $inRange1to5")
    println(s"Числа в диапазоне 10-20: $inRange10to20")
    
    // Автоматическое каррирование
    def multiplier(x: Int, y: Int): Int = x * y
    val curriedMultiplier = (multiplier _).curried
    
    val double = curriedMultiplier(2)
    val triple = curriedMultiplier(3)
    
    println(s"Удвоение чисел [1,2,3]: ${List(1, 2, 3).map(double)}")
    println(s"Утроение чисел [1,2,3]: ${List(1, 2, 3).map(triple)}")
  }
  
  // Задание 5: Мутабельные vs Иммутабельные коллекции
  def task5_MutableVsImmutable(): Unit = {
    println("Сравнение мутабельных и иммутабельных коллекций:")
    
    // ИММУТАБЕЛЬНЫЕ коллекции
    println("\n--- ИММУТАБЕЛЬНЫЕ коллекции ---")
    val immutableList = List(1, 2, 3)
    println(s"Исходный список: $immutableList")
    
    // Операции создают новые коллекции
    val newImmutableList = immutableList :+ 4
    println(s"После добавления 4: $newImmutableList")
    println(s"Исходный список не изменился: $immutableList")
    
    // МУТАБЕЛЬНЫЕ коллекции
    println("\n--- МУТАБЕЛЬНЫЕ коллекции ---")
    import scala.collection.mutable
    
    val mutableBuffer = mutable.Buffer(1, 2, 3)
    println(s"Исходный Buffer: $mutableBuffer")
    
    // Операции изменяют существующую коллекцию
    mutableBuffer += 4
    println(s"После добавления 4: $mutableBuffer")
    
    mutableBuffer(0) = 100  // изменение элемента
    println(s"После изменения первого элемента: $mutableBuffer")
    
    // Сравнение производительности
    println("\n--- Сравнение производительности ---")
    
    val largeImmutable = (1 to 1000).toList
    val largeMutable = mutable.Buffer.from((1 to 1000))
    
    // Иммутабельные - безопасны для многопоточности
    def processImmutable(data: List[Int]): List[Int] = {
      // Гарантия, что исходные данные не изменятся
      data.map(_ * 2).filter(_ > 100)
    }
    
    // Мутабельные - могут быть изменены
    def processMutable(data: mutable.Buffer[Int]): Unit = {
      // Опасность! Может изменить исходные данные
      for (i <- data.indices) {
        data(i) = data(i) * 2
      }
    }
    
    println("Иммутабельные:")
    println(s"Исходные: ${largeImmutable.take(5)}...")
    val processedImmutable = processImmutable(largeImmutable)
    println(s"После обработки: ${processedImmutable.take(5)}...")
    println(s"Исходные не изменились: ${largeImmutable.take(5)}...")
    
    println("\nМутабельные:")
    println(s"Исходные: ${largeMutable.take(5)}...")
    processMutable(largeMutable)
    println(s"После обработки: ${largeMutable.take(5)}...")
    println("Исходные ИЗМЕНИЛИСЬ!")
    
    // Преимущества иммутабельных коллекций
    println("\n--- Преимущества иммутабельных коллекций ---")
    println("• Безопасность в многопоточных приложениях")
    println("• Предсказуемость поведения")
    println("• Легче отлаживать")
    println("• Функциональный стиль программирования")
    
    println("\n--- Преимущества мутабельных коллекций ---")
    println("• Лучшая производительность для частых изменений")
    println("• Меньшее потребление памяти")
    println("• Удобство для императивного стиля")
  }
}