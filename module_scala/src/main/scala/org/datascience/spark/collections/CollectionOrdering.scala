package org.datascience.spark.collections

//case class Persona(edad: Int, nombre: String)

object CollectionOrdering {

  def main(args: Array[String]): Unit = {


//    val seqPersonas: Seq[Persona] = Seq(
//      Persona(23, "Miguel"),
//      Persona(20, "Kelly"),
//      Persona(23, "Luis"),
//      Persona(23, "Luis"),
//      Persona(21, "Luis"),
//      Persona(22, "Luis")
//    )
//
//    seqPersonas.foreach(println)
//
//    println("*******************************************")
//
//     val orderDesc: Ordering[Persona] = Ordering.by((per: Persona) => (per.nombre, per.edad) )
//    //    implicit val orderDesc : Ordering[Persona] = Ordering.by(persona => persona.edad)
//
//    seqPersonas.sorted(orderDesc).foreach(println)

    val palabras : Seq[String]  = List("Word","Word", "word", "word", "palabra1")

    palabras

      .filter(_ == "Word")

      .map(_.trim)

      .foreach(println)

    palabras.foreach(println)

    println("*******************************************")

    // Log(N)
    val set : Set[String] = palabras.toSet

    set.foreach(println)
  }


}
