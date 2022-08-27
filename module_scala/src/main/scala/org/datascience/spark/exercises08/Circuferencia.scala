package org.datascience.spark.exercises08

case class Circuferencia(radio: Double) extends Figura {

  override def calcularArea(): Double = radio * radio

}
