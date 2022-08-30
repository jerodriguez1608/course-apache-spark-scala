package org.datascience.spark

object pregunta_03 {

  def main(args: Array[String]): Unit = {

    val title : String ="abcdefghijklmnopk"

    title
      .map(world => {
        val newWorld: Int = world.toInt + 3
        newWorld.toChar
      }).foreach(println)

  }

}
