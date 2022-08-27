package org.datascience.spark

object OptionIntTest {


  def main(args: Array[String]): Unit = {

    val texto: String = null

    val oNumber: Option[Int] =
      try
        Option(texto.trim.toInt)
      catch {
        case _ => None
      }

    println(oNumber.map(_ + 100).getOrElse(0))

    println(oNumber)


  }

}
