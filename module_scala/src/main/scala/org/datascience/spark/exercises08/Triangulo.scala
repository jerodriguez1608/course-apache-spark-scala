package org.datascience.spark.exercises08

case class Triangulo(altura: Int, ancho: Int) extends Figura {

  override def calcularArea(): Double =  (altura * ancho) / 2

}
