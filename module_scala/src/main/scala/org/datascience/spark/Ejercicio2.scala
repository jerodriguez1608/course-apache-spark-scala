package org.datascience.spark

//Sr. Perez solicita una lista con las cadenas que son separadas por mayúsculas.
//Ejemplo: Texto: “SaveChangesInTheEditor”
//  Resultado esperado: {“Save”, “Changes”, “In”, “The”, “Editor”}

object Ejercicio2 {
  def main(args: Array[String]): Unit = {
    // INPUT
    val input = "SaveChangesInTheEditor"

    // testing with regex and declarative dev
//    val array = input.split("(?=[A-Z])")
//    val array = input.split("(?=\\p{Upper})")
//    var result = array.toSet
//    println(result)

    input
      .split("(?=\\p{Upper})")
      .foreach(println)
  }
}
