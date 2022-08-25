package org.datascience.spark

import java.time.{LocalDate, LocalDateTime, LocalTime}

object TypeDate {

  def main(args: Array[String]): Unit = {

    val date  : LocalDate =  LocalDate.now()
    println(date)

    val timestamp  : LocalDateTime =  LocalDateTime.now()
    println(timestamp)

    val hours  : LocalTime =  LocalTime.now()
    println(hours)
  }

}
