package org.datascience.spark.exercises08

case class Rectangulo(largo: Int , ancho: Int) extends Figura {

  override def calcularArea(): Double = largo * ancho

}
