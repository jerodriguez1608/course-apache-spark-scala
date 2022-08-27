package org.datascience.spark.exercises08

object TestUser {


  def resultadoDeCalcularElAreaFigura(figura: Figura): String = {
    s"El area de la figura es : ${figura.calcularArea()}"
  }



  def main(args: Array[String]): Unit = {


    val resultadoTriangulo = resultadoDeCalcularElAreaFigura(Triangulo(10,5)) // 25
    val resultadoCircuferencia = resultadoDeCalcularElAreaFigura(Circuferencia(10)) // 100
    val resultadoRectangulo = resultadoDeCalcularElAreaFigura(Rectangulo(10,20)) // 200

    println(resultadoTriangulo)
    println(resultadoCircuferencia)
    println(resultadoRectangulo)
  }


}
