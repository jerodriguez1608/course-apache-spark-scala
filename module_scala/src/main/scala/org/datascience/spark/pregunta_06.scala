package org.datascience.spark

import java.time.{LocalDate, LocalDateTime, Month}

object pregunta_06 {

  def main(args: Array[String]): Unit = {

    //fecha con salida : 25 de agosto del 1993 a las 3 hora y 24 minutos

    val fechaActual= LocalDateTime.of(1993,Month.AUGUST,25,3,24)

    val years : Int = fechaActual.getYear
    val month : Month = fechaActual.getMonth
    val day : Int = fechaActual.getDayOfMonth
    val hour : Int = fechaActual.getHour
    val minute : Int = fechaActual.getMinute

    println(s"La fecha es: ${day} de ${month} del ${years} a las ${hour} hora y ${minute} minutos")




  }

}
