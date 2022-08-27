package org.datascience.spark

object MapvsFlapMapArray {

  def main(args: Array[String]): Unit = {

    val array : Array [String] = Array(
      Array("A1","A2","A3")
      //Array("B01","B02","B03")
    )

    array
      .flatMap(array => array)
      .filter(letter => letter.containers("2"))
      .foreach(println)

  }

}
