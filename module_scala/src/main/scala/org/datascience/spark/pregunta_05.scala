package org.datascience.spark

object pregunta_05 {

  def main(args: Array[String]): Unit = {


    val world = "kenyi"
    var result: Int = 0

    try {

      result = world.toInt

    } catch {

      case e: Exception => println("Error: Ingrese un numero valido")


    }

  }

}
