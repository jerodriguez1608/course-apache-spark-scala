package org.datascience.spark
package org.datascience.spark
import java.time.{LocalDateTime, Month}
import java.time.LocalDate
import java.time.format.{DateTimeFormatter, FormatStyle}
import jdk.jfr.internal.tool.Main
import java.time.temporal.ChronoUnit._

object Ejercicio7_PC01 {

  def main(args: Array[String]): Unit = {

    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val fecha = LocalDate.parse("06/07/2016", formato)

    val mayorq = LocalDate.parse("01/01/1900", formato)

    val UltimaCopa = LocalDate.parse("15/07/2018", formato)



    if (fecha.isAfter(mayorq)) {

      val days = DAYS.between(fecha, UltimaCopa)
      print(days)
    }
    
  }

}
