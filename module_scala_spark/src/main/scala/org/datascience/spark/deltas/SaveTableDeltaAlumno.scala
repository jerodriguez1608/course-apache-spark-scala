package org.datascience.spark.deltas

import io.delta.tables.DeltaTable
import org.datascience.spark.DbColegio._
import org.datascience.spark.SparkInit._

import java.time.LocalDateTime

object SaveTableDeltaAlumno {

  def main(args: Array[String]): Unit = {

    val sparkInit = spark

    import spark.implicits._

//    DeltaTable.forPath(TB_ALUMNOS).toDF.show(false)


    val dfAlmunoNew = Seq(
      ("48842843", "Jose Aguilar", "2022-II", 11.10 ,LocalDateTime.now().toString , LocalDateTime.now().toString),
    )
      .toDF("dni", "alumno", "ciclo" , "promedio", "creationDate", "updateDate")


    dfAlmunoNew
      .write
      .format("delta")
      .mode("append")
      .save(TB_ALUMNOS)

//    DeltaTable.forPath(TB_ALUMNOS).toDF.show(false)


  }
}
