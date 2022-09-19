package org.datascience.spark.deltas

import org.datascience.spark.SparkInit._
import  org.datascience.spark.DbColegio._

import java.time.LocalDate
object CreateTbCursos {

  def main(args: Array[String]): Unit = {

    import spark.implicits._

    val tableCursos = Seq(
      ("10002", "Fisica", "23-A", "Arturo David", LocalDate.now().toString)
    ).toDF("id", "curso", "aula" , "docente", "fechaIni")

    tableCursos.write.format("delta")
      .save(TB_CURSOS)

  }
}
