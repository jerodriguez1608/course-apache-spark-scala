package org.datascience.spark
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

//Implementar una clase para que lea una fecha con el formato "dd/MM/yyyy" y valide que
//sea una fecha correcta, también la fecha debe ser mayor al 01/01/1900, la clase debe
//devolver el numero de días transcurridos desde la fecha ingresada hasta la ultima copa del
//mundo.

object Ejercicio7 {
  val lastWorldCup = "14/06/2018"

  def getDaysUntilWC(dateToEvaluate: String): Int ={
    val slashs = dateToEvaluate.count(_ == '/')
    if (slashs == 2){
      try{
        val splitedDate = dateToEvaluate.split("/")
        val dd = splitedDate(0).toInt
        val MM = splitedDate(1).toInt
        val yy = splitedDate(2).toInt

        if (dd > 31 || MM > 12 || 1900 > yy || yy > 2018){
          return -1
        }else{
          val date1 = LocalDateTime.of(yy, MM, dd, 0, 0)
          val date2 = LocalDateTime.of(2018, 6, 14, 0, 0)
          val result = ChronoUnit.DAYS.between(date1, date2)
          return result.toInt
        }
      } catch {
        case exception: Exception => -1
      }
    }else{
      return -1
    }

  }

  def main(args: Array[String]): Unit = {
//    val dateToEvaluate = "13/06/1888"
    val dateToEvaluate = "25/12/2011"
    val result = getDaysUntilWC(dateToEvaluate)
    if (result > 0){
      println("Hay %d días desde la fecha %s hasta la copa del mundo %s".format(result, dateToEvaluate, lastWorldCup))
    }else{
      println("La fecha indicada %s, no cumple con el formato dd/MM/yyyy".format(dateToEvaluate))
    }
  }
}
