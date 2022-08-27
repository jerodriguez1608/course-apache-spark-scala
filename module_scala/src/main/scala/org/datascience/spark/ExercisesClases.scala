package org.datascience.spark

case class Persona(nombre: String, edad: Int, dni: String) {

  override def toString: String =
    s"Se llama ${nombre} su dni es ${dni} y tiene ${edad} anios"

}

object ExercisesClases {

  def main(args: Array[String]): Unit = {

    val persona = Persona("Jhon", 50, "3939933332")

    println(persona)

  }

}
