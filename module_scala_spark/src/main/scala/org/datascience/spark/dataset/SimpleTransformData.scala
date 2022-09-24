package org.datascience.spark.dataset

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import java.time.LocalDate

case class Banda(id: BigInt, name: String, hometown: String, year: BigInt)
case class OutputBanda(nombre: String, ciudad: String, antiguedad: String)

object SimpleTransformData {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SimpleTransformData")
      .getOrCreate();

    import spark.implicits._

    val dataSetBands =
      spark
        .read
        .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\bands\\bands.json")
        .as[Banda]

//        .withColumn("NEWCOLUMN_2",
//          when(lit(2022) - $"year" < 40, lit("NUEVA"))
//            .when(lit(2022) - $"year" < 50, lit("SEMI NUEVA"))
//            .when(lit(2022) - $"year" < 60, lit("ANTIGUA"))
//            .otherwise(lit("UNDEFINED"))
//        )

    dataSetBands
      .filter(band => band.name.contains("e"))
      .map(band => {
        val yearNow = LocalDate.now().getYear()
        val diffYear : Int = yearNow - band.year.bigInteger.intValue()

        val antiguedad = {
          if(diffYear < 40) "NUEVA"
          else if(diffYear < 50) "SEMI NUEVA"
          else "ANTIGUA"
        }
        // nombre , ciudad, antiguedad
        OutputBanda(band.name, band.hometown, antiguedad)
      }
      )
      .show(false)

  }


}
