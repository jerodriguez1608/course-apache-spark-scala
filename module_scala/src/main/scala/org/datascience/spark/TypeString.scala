package org.datascience.spark

object TypeString {

  def main(args: Array[String]): Unit = {

    val title : String = "El principito"

    println(title.substring(3))
    println(title.substring(0,2))
    println(title.substring(5,9))

  }

}
