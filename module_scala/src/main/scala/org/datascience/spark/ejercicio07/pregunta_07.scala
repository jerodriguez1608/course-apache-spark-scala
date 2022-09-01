package org.datascience.spark.ejercicio07

object pregunta_07 {

  /** *
   * Implementar una clase para que lea una fecha con el formato "dd/MM/yyyy" y
   * valide que sea una fecha correcta, también la fecha debe ser mayor al 01/01/1900,
   * la clase debe devolver el numero de días transcurridos desde la fecha ingresada
   * hasta la ultima copa del mundo.
   *
   * @param args
   */

  def resultadoValidarFecha(world: fecha): Unit ={
    world.validarFecha()
  }

  def main(args: Array[String]): Unit = {

    //formato de fecha en Int
    //dia,mes,anio
    val resultadoFecha = resultadoValidarFecha(fecha(1,9,2022))

  }
}
