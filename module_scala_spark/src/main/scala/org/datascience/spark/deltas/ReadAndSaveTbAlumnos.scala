package org.datascience.spark.deltas

import io.delta.tables.DeltaTable
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.datascience.spark.DbColegio._
import org.apache.spark.sql.functions._
import java.time.LocalDateTime
import javax.xml.crypto.Data

object ReadAndSaveTbAlumnos {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate();

    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "1")
    spark.sqlContext.setConf("spark.default.parallelism", "1")

    import spark.implicits._

    //DATA NUEVA
    val dfAlumnosNuevos = dataAlumnosNuevos(spark)

    val deltaAlumnos = DeltaTable.forPath(TB_ALUMNOS)

    deltaAlumnos.toDF.show(false)

    deltaAlumnos.as("m")
      .merge(
        dfAlumnosNuevos.as("in"),
        f"m.dni = in.dni "
      )
      .whenMatched(
        "m.ciclo <> in.ciclo or m.alumno <> in.alumno or m.promedio <> in.promedio"
      )
      .update(
        Map(
          "dni" -> lit("in.dni"),
          "alumno" ->  lit("in.alumno") ,
          "ciclo" -> lit("in.ciclo") ,
          "promedio" -> lit("in.promedio") ,
          "creationDate" -> lit("m.creationDate") ,
          "updateDate" -> lit("in.updateDate")
        )
      )
      .whenNotMatched(
        "in.promedio > 11"
      )
      .insertAll()
      .execute()

    val alumnosUpdate = DeltaTable.forPath(TB_ALUMNOS)

    alumnosUpdate.toDF.show(false)

  }

  def dataAlumnosNuevos(spark: SparkSession): DataFrame = {
    import spark.implicits._

    val tablaAlumnos = Seq(
      ("77676887", "Daniel Aguirre Rodriguez", "2023-II", 11.4, LocalDateTime.now().toString, LocalDateTime.now().toString),
      ("34443333", "Jhon Rodriguez", "2021-II", 10.4, LocalDateTime.now().toString, LocalDateTime.now().toString),
      ("93838222", "Juan Silva", "2021-II", 18.4, LocalDateTime.now().toString, LocalDateTime.now().toString),
      ("48842843", "Jose Aguilar ", "2021-II", 20.0, LocalDateTime.now().toString, LocalDateTime.now().toString),
      ("44433333", "Alberto Borda", "2021-II", 17.4, LocalDateTime.now().toString, LocalDateTime.now().toString)
    )
      .toDF("dni", "alumno", "ciclo" , "promedio", "creationDate", "updateDate")

    tablaAlumnos

    /**
     *
     * |44433333|Alberto Borda           |2021-II|17.4    |2022-09-16T21:10:29.183727| INSERT - 1
     * |77676887|Daniel Aguirre|2022-II|11.4    |2022-09-16T20:53:15.440293200| PRE-IMAGE -  2
     * |77676887|Daniel Aguirre Rodriguez|2023-II|10.4    |2022-09-16T21:10:29.183727| | POST-IMAGE - 2 .. 2022-09-16T21:10:29.183727
     * |93838222|Juan Silva              |2021-II|18.4    |2022-09-16T21:10:29.183727| INSERT - 2 2022-09-16T21:10:29.183727
     * |77676887|Daniel Aguirre Rodriguez|2023-II|10.4    |2022-09-16T21:10:29.183727|  PRE-IMAGE -  3 ..
     * |77676887|Daniel Aguirre Rodriguez|2024-II|12.4    |2022-09-16T21:10:29.183727| | POST-IMAGE - 3 ..
     *
     */
  }

}
