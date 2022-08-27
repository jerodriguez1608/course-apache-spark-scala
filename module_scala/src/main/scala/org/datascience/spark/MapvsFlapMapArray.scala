package org.datascience.spark

object MapvsFlapMapArray {

  def main(args: Array[String]): Unit = {


    val array : Array[Array[String]] = Array(
      Array("A1", "A2", "A3"),
      Array("B01", "B02", "B03")
    )

    /**
     *  ARRAY [ ARRAY [ 01 11 ] ARRAY [02 03 ]
     *  FLAT MAP
     *  ARRAY [ 01, 11 , 02 ,03]
     */


    array
//      .flatMap(array => array)
      .filter(letter => letter.contains("2"))
      .foreach(println)

  }


}
