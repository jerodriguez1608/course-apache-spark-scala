package org.datascience.spark.exercises

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import java.time.LocalDate

object Pregunta02 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  /**
   * ----------- SCHEMA ----------
   * root
   * |-- hometown: string (nullable = true)
   * |-- id: long (nullable = true)
   * |-- name: string (nullable = true)
   * |-- year: long (nullable = true)
   *
   * +-----------+---+------------------+----+
   * |hometown   |id |name              |year|
   * +-----------+---+------------------+----+
   * |Sydney     |1  |AC/DC             |1973|
   * |Sydney     |5  |PVT               |1986|
   * |London     |0  |Led Zeppelin      |1968|
   * |London     |6  |The Rolling Stones|1968|
   * |London     |7  |The Clash         |1968|
   * |Los Angeles|3  |Metallica         |1981|
   * |Los Angeles|8  |Weezer            |1993|
   * |Los Angeles|9  |Bad Religion      |1979|
   * |Liverpool  |4  |The Beatles       |1960|
   * +-----------+---+------------------+----+
   *
   * 1. Retornar cuantas bandas hay por ciudad(hometown)
   *
   * Output Dataframe::
   * Lima | 3
   * Lima | 4
   *

   *
   *
   *

   *
   */
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Pregunta02")
      .getOrCreate()

    import spark.implicits._

    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read
      .json(PATH.BANDS_PATH_URL)

    df.printSchema()
    df.show(false)

    df
      .withColumn("NEWCOLUMN_2",
        when(lit(2022) - $"year" < 40, lit("NUEVA"))
          .when(lit(2022) - $"year" < 50, lit("SEMI NUEVA"))
          .when(lit(2022) - $"year" < 60, lit("ANTIGUA"))
          .otherwise(lit("UNDEFINED"))
      )
      .show(false)

    //    val dfModificado = df
    //      .withColumn("NEWCOLUMN_1",
    //        when(length($"name") > 10, lit("VERDAD"))
    //          //        .when( length($"name") > 12, lit("VERDAD 2") )
    //          .otherwise(lit("FALSE"))
    //      )
    //
    //    df.printSchema()
    //    dfModificado.show(false)


  }

}
