package org.datascience.spark

import java.time._
import java.time.temporal.ChronoUnit

object Ejercicio1 {
  def main(args: Array[String]): Unit = {
//    val instant: Instant = Instant.now()
//    val zoneId: ZoneId = ZoneId.of("America/Edmonton")
//    ejercicio6(date = LocalDateTime.now())
//    print(ejercicio3().capitalize)
//    investigaion1()
    ejercicio10()
  }

  def ejercicio1(): Unit = {
    val textoEjercicio01: String = "rossmery, miguel , domitila, jose, luis, kelly, elmer"
    textoEjercicio01.split(",").foreach((word) => println(word.trim.capitalize))
  }

  def ejercicio2(): Unit = {
    val texto = "SaveChangesInTheEditor"
    texto.split("(?=\\p{Upper})").foreach(println)
  }


  def ejercicio3(wordToTranslate: String = "There's-a-starman-waiting-in-the-sky",originalAlphabet: String = "abcdefghijklmnopqrstuvwxyz", index: Int= 0, k: Int = 3, acc: String = ""): String = {
//    Opcion 1:
//    val alphabetRotated = originalAlphabet.substring(k, originalAlphabet.length) + originalAlphabet.substring(0,k)
//    var resultado = acc
//    var wtTranslate = wordToTranslate
//    if (wordToTranslate.length== 0) {
//      return resultado
//    }
//    var letter = wordToTranslate(index).toLower
//    println(letter)
//    if (originalAlphabet.contains(letter)) {
//      wtTranslate = wordToTranslate.substring(1, wordToTranslate.length)
//      resultado + ejercicio3(wordToTranslate = wtTranslate, originalAlphabet=originalAlphabet, acc = alphabetRotated(originalAlphabet.indexOf(letter)).toString)
//    } else {
//      wtTranslate = wordToTranslate.substring(1, wordToTranslate.length)
//      resultado + ejercicio3(wordToTranslate = wtTranslate, originalAlphabet=originalAlphabet, acc = letter.toString)
//    }

//    Opcion 2:
    val NUEVO_ABECEDARIO = originalAlphabet.substring(k, originalAlphabet.length) + originalAlphabet.substring(0,k)
    val arrayLetras: Array[String] = wordToTranslate
      .split("").map(_.toLowerCase())
    arrayLetras.foreach(println)

    def fnTransformation(letter: String) : String = {
      if (originalAlphabet.contains(letter)) {
        val index = originalAlphabet.indexOf(letter)
        val letterRotation = NUEVO_ABECEDARIO(index).toString()
        letterRotation
      } else {
        letter
      }
    }

    val arrayNewLetters : Array[String] = arrayLetras.map(fnTransformation)
    val collectArray: String = arrayNewLetters.reduce((w1, w2) => w1 + w2)
    collectArray.capitalize
  }

  def ejercicio4(): Unit = {
//    val s1: String = "and"
//    val s2: String = "art"

    val s1: String = "hi"
    val s2: String = "world"

    println(!s1.filter((letter: Char) => s2.contains(letter)).isEmpty())
  }

  def investigaion1(): Unit = {
    // 1. mkString
    // Crear una lista
    val m1 = List(2, 3, 5, 7, 8)

    // Aplicar el metodo mkString, el cual unir'a todos los elementos con *
    val result = m1.mkString("*")

    // Displays output
    println(result)

    // 2. matches by regular expression
    val result2 = "Ayushi".matches(".i.*")
    // Display output
    println("Result : " + result2)

    // 3. concat
    val result3 = "Geeks".concat("forGeeks")
    // Display output
    println("Result : " + result3)
  }

  def ejercicio05(word: String): Unit = {
    val oNumber: Option[Int] =
      try
        Option(word.trim.toInt)
      catch {
        case _ => None
      }
    println(oNumber.map((number) => number).getOrElse(0))
    println(oNumber)
  }

  def ejercicio6(date: LocalDateTime): Unit = {
    println(s"${date.getDayOfMonth} de ${date.getMonth} de ${date.getYear} a las ${date.getHour} horas y ${date.getMinute} minutos")
  }

  def ejercicio7(): Unit = try {
    val initialDate = "12/12/1990"
    val df: DateFormat = new SimpleDateFormat("dd/MM/yyyy")
    df.setLenient(false)
    df.parse(initialDate)
    println(true)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dateAsArray = initialDate.split("/")
    val resultado =LocalDate.of(dateAsArray(2).toInt, dateAsArray(1).toInt, dateAsArray(0).toInt)
    println(resultado.isAfter(LocalDate.of(1900, 1,1)))
    println(ChronoUnit.DAYS.between(LocalDate.of(1900, 1,1), resultado))
  } catch  {
    case e: ParseException => println(false)
  }

  def investigacion02(): Unit = {
    // 1. isEmpty(), nos permite conocer si existe un elemento al menos dentro del container e Option
    val some: Option[Int] = Some(20)
    val none: Option[Int] = None

    val x = some.isEmpty
    val y = none.isEmpty

    println(x) // false
    println(y) // true

    // 2. Map() para iterar sobre un option
    case class Car(model: String, owner: Option[Person], registrationPlate: Option[String])
    case class Person(name: String, age: Int, drivingLicense: Option[String])

    def ownerName(car: Car) : Option[String] = {
      car.owner.map(p => p.name)
    }

  }

  def ejercicio09() : Unit = {
    val numbers = List(1,2,3,4,5,6,7,8,9,10)
    println(numbers.filter(_ > 2).map(_ * 3).reduce(_ + _))
  }

  def ejercicio10() : Unit = {
    val lengths = List("Mercedes", "Hannah", "Emily")
    lengths
      .flatMap(_.split(""))
      .map(_.toLowerCase)
      .groupBy(identity)
      .map(v => (v._1, v._2.length))
      .foreach(println)
  }
}