package org.datascience.spark.deltas

import org.apache.spark.sql.SparkSession
import org.datascience.spark.DbColegio._
import org.datascience.spark.SparkInit._

import java.time.LocalDateTime

object CreateTbAlumnos {

  def main(args: Array[String]): Unit = {

  import spark.implicits._

    val tablaAlumnos = Seq(
      ("48842843", "Jose Aguilar", "2022-II", 11.10 ,LocalDateTime.now().toString , LocalDateTime.now().toString),
    )
      .toDF("dni", "alumno", "ciclo" , "promedio", "creationDate", "updateDate")

    tablaAlumnos
      .write
      .format("delta")
      .save(TB_ALUMNOS)

  }
}
