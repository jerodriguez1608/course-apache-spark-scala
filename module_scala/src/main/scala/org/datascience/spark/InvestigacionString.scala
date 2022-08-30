package org.datascience.spark

//Investigar 3 nuevas funciones aplicadas para un String en Scala,
//aplicarlas en Intellij y explicar su funcionamiento documentando el código.

object InvestigacionString {
  def main(args: Array[String]): Unit = {
    //  charAt devuelve el caracter que se indica como indice.
    //  parameter: index
    //  return: char
    val s1 = "Erase una vez"
    println("Se requere el caracter de la posicion 7, considerando que su índice es 6")
    println("cadena: " + s1, " el caracter en la posición 7 es: " + s1.charAt(6) + "\n")

    //  compareTo, evalúa una cadena S1 contra una cadena S2
    //  Si las cadenas son iguales devuelve un valor entero igual a cero "0"
    //  Si S1 es menor que S2 entonces se devuelve un número negativo que es la diferencia del valor de los caracteres
    //  Si S1 es mayor que S2 entonces se devuelve un número positivo
    //  parameter: String
    //  return: Integer
    val s2 = "ERASE UNA VEZ"
    println("Devuelve 0, debido a que las son iguales")
    println("Cadena S1: " + s1.toLowerCase, " cadena S2: " + s2.toLowerCase)
    println(s1.toLowerCase.compareTo(s2.toLowerCase) + "\n")


    //  Format es usado para formatear cadenas, pasando parámetros,
    //    donde "%d" es usado cuando el parametro es entero y "%f" cuando el parametro es double o float, %s cuando es String
    val valueList = List(("Jorge", 19, 190.60, "cine"), ("Maria", 25, 155.55, "teatro"), ("Alberto", 27, 211.10, "concientro"), ("Ana", 26, 350.50, "recital"))
    for (value <- valueList) {
      val s1 = value._1
      val d1 = value._2
      val f1 = value._3
      val s2 = value._4
      val messageSuggested = s"${s1} con edad de %d anios, gastó %f en el %s".format(d1, f1, s2)
      println(messageSuggested)
    }
  }
}
