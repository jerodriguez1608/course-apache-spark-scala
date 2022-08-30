package org.datascience.spark

object pregunta_01 {

  def main(args: Array[String]): Unit = {

    val title: String = "rossmery,miguel,domitila,jose,luis,kelly,elmer"

    val nom01 = title.substring(0,8).capitalize
    val nom02 = title.substring(9,15).capitalize
    val nom03 = title.substring(16,24).capitalize
    val nom04 = title.substring(25,34).capitalize
    val nom05 = title.substring(35,40).capitalize
    val nom06 = title.substring(41,46).capitalize

    val output=s"$nom01, $nom02, $nom03, $nom04, $nom05, $nom06"
    println(output)

  }

}
