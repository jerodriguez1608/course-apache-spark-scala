package org.datascience.spark.ejercicio07
import java.sql.Date
import java.text.{ParseException, SimpleDateFormat}
import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime}

/** *
 * Implementar una clase para que lea una fecha con el formato "dd/MM/yyyy" y
 * valide que sea una fecha correcta, también la fecha debe ser mayor al 01/01/1900,
 * la clase debe devolver el numero de días transcurridos desde la fecha ingresada
 * hasta la ultima copa del mundo.
 *
 * @param args
 */


case class fecha(dia: Int, mes: Int, anio:Int) {

  def validarFecha(): Unit = {
    var result: String = ""
    try {
      val fecha = LocalDate.of(anio, mes, dia)
      val fechaMenor = LocalDate.of(1900, 1, 1)
      val ultimoMundial = LocalDate.of(2018, 6, 15)
      val diferencia = ChronoUnit.DAYS.between(ultimoMundial, fecha)
      val diferencia2 = ChronoUnit.DAYS.between(fechaMenor, fecha)
      result = diferencia.toString
      if(diferencia2 < 0){
        result = "False"
      }
      println(result)
    } catch {
      case e: Exception => result = "Fecha incorrecta"
        println(result)
    }
  }

}
