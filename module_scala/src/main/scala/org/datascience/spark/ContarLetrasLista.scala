package org.datascience.spark


object ContarLetrasLista {

  /**
   * CONTAR LAS LETRAS DE TODAS LAS PALABRAS
   *
   * U -> 2
   * N -> 1
   * O -> 3
   * .
   * .
   * .
   *
   */
  def main(args: Array[String]): Unit = {

  val palabras = Seq("uno", "dos", "tres","cuatro")

    palabras
      .map(letras => letras.toCharArray)
      /**
       * Array
       * ( Array (u,n,o )
       * Array (d,o,s)
       * ...
       * )
       */
      .flatMap(arrayLetras => arrayLetras)
      .groupBy(key => key)
      .map(tuple => (tuple._1, tuple._2.length))
      .foreach(
        arrayLetras => println(arrayLetras)
      )


  }

}
