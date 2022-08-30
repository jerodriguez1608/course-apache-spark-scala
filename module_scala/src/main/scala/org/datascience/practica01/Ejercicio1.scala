package org.datascience.spark

//    Sr.Juan solicita poner en mayúscula la primera letra de cada nombre de la siguiente lista:
//    “rossmery, miguel, domitila, jose, luis, kelly, elmer”

object Ejercicio1 {
  def main(args: Array[String]): Unit =
  {
    // testing with declarative dev
    val nameString = "rossmery, miguel, domitila, jose, luis, kelly, elmer"

//    val nameArray = nameString.replaceAll(" ","").split(',')
//    for (z <- nameArray){
//      println(z.capitalize)
//    }

    nameString
      .replaceAll(" ","")
      .split(',')
      .map(_.capitalize)
      .foreach(println)
  }
}
