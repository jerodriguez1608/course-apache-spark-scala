package org.datascience.spark

object pregunta_02 {

  def main(args: Array[String]): Unit = {

    val palabra: String="SaveChangesInTheEditor"

    palabra.split("(?=\\p{Upper})").foreach(println)



  }

}
