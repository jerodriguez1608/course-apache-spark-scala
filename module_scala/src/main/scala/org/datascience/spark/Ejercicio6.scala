package org.datascience.spark

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

//Sr. Perez solicita un algoritmo que le entregue un documento en donde la fecha tenga el
//siguiente formato: 25 de Agosto del 1993 a las 3 horas y 24 minutos

object Ejercicio6 {

  def main(args: Array[String]): Unit = {
    val myformat = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' hh 'horas con' mm 'minutos'")
    val date = Calendar.getInstance()
    val current = date.getTime
    val currentFormatted = myformat.format(current)
    println(currentFormatted)

    val currentDate = LocalDateTime.now
    val myFormat = DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy 'a las' hh 'horas con' mm 'minutos'")
    println(myFormat.format(currentDate))
  }
}
