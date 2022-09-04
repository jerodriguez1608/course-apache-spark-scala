package org.datascience.spark.threads

import java.time.LocalTime
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Ejercicio14Futures {

  var GLOBAL_SUM = 0
  // 6 segundos
  def m1(): Int = {
    println(s"Name Thread m1() :: ${Thread.currentThread().getName}")
    var sum = 0

    for (i <- 0 to 5) {

      sum = sum + i
      Thread.sleep(1000)

    }

    sum
  }

  // 11 seg
  def m2(): Int = {

    println(s"Name Thread m2() :: ${Thread.currentThread().getName}")

    var sum = 0

    for (i <- 0 to 10) {

      sum = sum + i
      Thread.sleep(1000)

    }

    sum
  }

  def main(args: Array[String]): Unit = {
    println(s"Name Thread Principal :: ${Thread.currentThread().getName}")
    //    // 17 segundos
    //    println(s"${LocalTime.now()} ************ ")
    //
    //    val res1 = m1()
    //    val res2 = m2()
    //
    //    println(res1 + res2)

    println(s"${LocalTime.now()} ************ ")

    // 11 segundos
    val f1 = Future {
      m1()
    }

    val f2 = Future {
      m2()
    }

    f1
      .zip(f2)
      .map(
        {
          case (r1, r2) => r1 + r2
        }
      )
      .foreach(sum => {

        println(s"La suma es $sum")

        println(s"${LocalTime.now()} ************ ")
      })

//    Thread.sleep(12000)
    println(s"${LocalTime.now()} ************ ")

  }


}
