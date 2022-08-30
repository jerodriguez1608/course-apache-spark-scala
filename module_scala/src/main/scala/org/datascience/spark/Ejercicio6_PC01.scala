package org.datascience.spark
import java.time.{LocalDateTime, Month}
import java.time.LocalDate
import java.time.format.{DateTimeFormatter, FormatStyle}
import jdk.jfr.internal.tool.Main
import java.time.temporal.ChronoUnit._

object Ejercicio6_PC01 {
  def main(args: Array[String]): Unit = {

    val date = LocalDateTime.now
    val localDateTime = DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy 'a las' HH 'y' mm 'minutos'")


    println(localDateTime.format(date))



  }


}
