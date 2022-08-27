package org.datascience.spark

object Test03String {

  val ABECEDARIO = "abcdefghijklmnopqrst"

  /**
   *
   * 1. middle = Array [m,i,d,d,l,e]
   * 2. abcederia buscar index
   *
   *
   * Plan A
   * 3. index + var[Rotation]
   * 4. obtener esa letra position
   *
   * Plan B
   * 3. index
   * 4. abecderario -> subt(index, index + var[Rotation]  + 1 )
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val textoInicial: String = "middle"
    val rotation: Int = 2

    val arrayLettras: Array[String] = textoInicial
      .split("")


    def functionTransfomation(letter: String ) : String = {
      val index = ABECEDARIO.indexOf(letter)
      val letterRotation = ABECEDARIO.substring(index + rotation, index + rotation + 1)
      letterRotation
    }

    val arrayNewLetters : Array[String] =
      arrayLettras
        .map(functionTransfomation)

    val collectArray =  arrayNewLetters.reduce((w1,w2) => w1 + w2)

    println(collectArray)


  }

}
