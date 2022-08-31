package org.datascience.spark

object pregunta_04 {

  def main(args: Array[String]): Unit = {

    val title1 : Array[String] = "hello".split("")
    val title2 : Array[String] = "whorld".split("")

    title1
      .map(world =>{
        val newWorld=world.filter(_ == 'h')
        newWorld
      })
      .foreach(println)

  }

}
