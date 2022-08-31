package org.datascience.spark

object pregunta_04 {

  def main(args: Array[String]): Unit = {

    val title1 : Array[String] = "hello".split("")
    val title2 : Array[String] = "world".split("")

    var resultYES: String = ""
    var resultNO: String = ""


    for (world <- title1){
      //println(world)
        for (world2 <- title2) {
          //println(world2)

          if (world == world2) {
            resultYES = "yes"
          } else {
            resultNO = "no"
          }
        }
    }

    if (resultYES == "yes") {
      println("YES")
    }else {
      println("NO")
    }

  }

}
