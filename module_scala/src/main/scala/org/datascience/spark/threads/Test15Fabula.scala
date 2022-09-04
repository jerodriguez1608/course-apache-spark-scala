package org.datascience.spark.threads

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import Thread.sleep

object Test15Fabula {

  val pasos = 10

  def caminoTortuga(): Boolean = {

    for (i <- 1 to pasos) {
      sleep(1000)
      println(s"Tortuga esta en el paso $i")
    }

    true

  }

  def caminoLiebre(): Boolean = {

    var contarPasos = 0

    val random = scala.util.Random

    import scala.util.control.Breaks._

    breakable {
      while (true) {

        if (random.nextInt(100) < 80) {
          contarPasos += 1
          sleep(100)
          println(s"Liebre esta en el paso $contarPasos")
        } else {
          sleep(1000)
          println(s"Liebre  se quedo dormida")
        }

        if (contarPasos == pasos) break

      }
    }

    true
  }

  def main(args: Array[String]): Unit = {

    val futureTorttle = new Thread{
      override def run (): Unit = {
        caminoTortuga()

      }
    }

    val futureLiebre = new Thread{
      override def run (): Unit = {
        caminoLiebre()
      }
    }
//
//    val futureTorttle = Future {
//      caminoTortuga()
//    }
//
//    val futureLiebre = Future {
//      caminoLiebre()
//    }

    futureTorttle.start()
    futureLiebre.start()

    while (futureTorttle.isAlive || futureLiebre.isAlive ) {
      println("La carrera SIGUEE")
      sleep(1000)
    }

    if(!futureLiebre.isAlive) {
      println("******************** GANO LA LIEBRE ************")
    }

    if(!futureTorttle.isAlive) {
      println("******************** GANO LA TORTUGA ************")
    }


  }


}
