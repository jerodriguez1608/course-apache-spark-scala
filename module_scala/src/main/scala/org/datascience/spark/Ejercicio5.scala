package org.datascience.spark

//Solución para convertir un String a Numero que no tiene un valor numerico

object Ejercicio5 {
  def stringToIntControl(cadena: String): Int = {
    try {
      cadena.toInt
    } catch {
      case ex: Exception => 0
    }
  }

  def main(args: Array[String]): Unit = {
    val cadena = "Esto_no_es_un_número"
    //    val cadena = "123"
    val numero = stringToIntControl(cadena)
    println(numero)
  }
}
