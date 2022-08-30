package org.datascience.spark

object Ejercicio1_PC01 {

  def main(args: Array[String]): Unit = {

    /**
     * Sr. Juan solicita poner en mayúscula la primera
     * letra de cada nombre de la siguiente lista
     */
    val textoEjercicio01: String = "rossmery, miguel , domitila, jose, luis, kelly, elmer"

    textoEjercicio01
      .split(",")
      .map(_.trim.capitalize)
      .foreach(println)
  }
}
