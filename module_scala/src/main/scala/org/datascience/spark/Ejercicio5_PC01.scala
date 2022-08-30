package org.datascience.spark
import scala.util.Try
object Ejercicio5_PC01 {

  def main(args: Array[String]): Unit = {


    def tryToInt( s: String ) = Try(s.toInt).toOption

    val valor=tryToInt("hola")

    println("Type of var1: "+valor.getClass)

  }



}
