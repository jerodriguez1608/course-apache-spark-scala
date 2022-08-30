package org.datascience.spark

object Ejercicio3_PC01 {

  def main(args: Array[String]): Unit = {

    val ABECEDARIO = "abcdefghijklmnopqrstuvwxyz"

    val textoInicial: String = "middle"

    val rotation: Int = 2

    val arrayLettras: Array[String] = textoInicial
      .split("")

    def functionTransfomation(letter: String): String = {
      val index = ABECEDARIO.indexOf(letter)
      val letterRotation = ABECEDARIO.substring(index + rotation, index + rotation + 1)
      letterRotation}

      val arrayNewLetters: Array[String] =
        arrayLettras
          .map(functionTransfomation)

      val collectArray = arrayNewLetters.reduce((w1, w2) => w1 + w2)

      println(collectArray)
    }

}


