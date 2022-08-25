package org.datascience.spark

import com.typesafe.scalalogging.Logger._

object TypeNumber {

  def main(args: Array[String]): Unit = {

    val number1 : Int = 100
    val number2 : Double = 100.9

    val textNumber1 : String = "999"

    val sum = textNumber1.toInt + number1

    println(sum)

    val textNumber2: String = "199233"

    def convertNumberFromText(txtNum: String , number2: Int) : String = {
      try {
        (txtNum.toInt + number2).toString
      } catch {
        case _ => {
          "NaN"
        }
      }
    }
    val sum2 = convertNumberFromText(textNumber2, number1)

    println(sum2)
  }

}
