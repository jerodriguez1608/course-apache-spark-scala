package org.datascience.spark

object IfElseConFilter {

  def main(args: Array[String]): Unit = {

    var number = 0

    if (number < 999) {
      number = number * 5
    } else if (number > 120000) {
      number = number * 3
    }

    /**
     * FILTER = ACORTAR UNIVERSO
     * MAP = TRABAJAS SOBRE EL UNIVERSO
     */
    Seq(1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15)
      .filter(num => num < 999 || num > 120000)
      .map(num => {
        if (num < 9) {
          num * 5
        } else if (num > 12) {
          num * 3
        }
      })
      .foreach(println)

  }

}
