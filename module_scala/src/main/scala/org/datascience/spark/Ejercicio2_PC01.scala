package org.datascience.spark



object Ejercicio2_PC01 {

  def main(args: Array[String]): Unit = {

    /**
    Sr. Perez solicita una lista con las cadenas que son separadas por mayúsculas.
     */

    val textoEjercicio02: String = "SaveChangesInTheEditor"

    textoEjercicio02.split("(?=[A-Z])").foreach(println)




  }

}
