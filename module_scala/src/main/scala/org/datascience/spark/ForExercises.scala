package org.datascience.spark

object ForExercises {

  def main(args: Array[String]): Unit = {

    val array = Array("A0", "A1", "A2", "A3")

    for (world <- array) {
      println(world)
    }

    for ((obj, index) <- array.zipWithIndex) {
      println(s"${obj} con indice: ${index}")
    }

    val map: Map[Int, String] = Map(
      (1001, "Lima"),
      (1002, "Trujillo")
    )

    for ((key, value) <- map) {
      println(s"${key} y su valor es ${value}")
    }

    for ( tupleA <- map) {
      println(s"${tupleA._1} y su valor es ${tupleA._2}")
    }

    val tupless : Array[(Int,String,Double)] = Array(
      (1001, "Lima", 10.3),
      (1002, "Trujillo", 133.3)
    )

    for (tuple <- tupless) {
      println(s"${tuple._1} ,, ${tuple._2} , ,, ${tuple._3}")
    }

  }


}
