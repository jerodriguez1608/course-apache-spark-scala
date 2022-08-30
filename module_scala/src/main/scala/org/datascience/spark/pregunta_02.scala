package org.datascience.spark

object pregunta_02 {

  def main(args: Array[String]): Unit = {

    println("-----------Opcion01-----------")
    val palabra: String="SaveChangesInTheEditor"

    palabra.split("(?=\\p{Upper})").foreach(println)

  }

}
