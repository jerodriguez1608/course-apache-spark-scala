package org.datascience.spark.exercises

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Pregunta03 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  /**
   * ----------- SCHEMA ----------
   * root
   * |-- guitarType: string (nullable = true)
   * |-- id: long (nullable = true)
   * |-- person: string (nullable = true)
   * |-- model: string (nullable = true)
   *
   * +----------------------+---+-------+------------+
   * |guitarType            |id |person   |model       |
   * +----------------------+---+-------+------------+
   * |Electric double-necked|0  |Gibson |EDS-1275    |
   * |Electric double-necked|10 |John   |EDS-1344    |
   * |Electric              |5  |Fender |Stratocaster|
   * |Electric              |1  |Gibson |SG          |
   * |Acoustic              |2  |Taylor |914         |
   * |Acoustic              |3  |Ishanna|980         |
   * |Electric              |3  |ESP    |M-II        |
   * +----------------------+---+-------+------------+
   *
   *
   * 3. Agrupar por tipo de guitarra y mostrar  las persona y el modelo de guitarra que tienen , ejemplo:
   * +----------------------+---+-------+------------+
   * |guitarType            |person_model|
   * +----------------------+---+-------+------------+
   * |Electric double-necked| (Gibson, EDS-1275), (John,EDS-1344) |
   * |Electric              | (Fender, Stratocaster , ...)|
   * ...
   *
   *
   */
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Pregunta03")
      .getOrCreate()
    import spark.implicits._
    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read
      .json(PATH.GUITARS_PATH_URL)

    val df2 = df
      .withColumn("structNew", struct("guitarType", "id", "person", "model"))

    df2.printSchema()

    df2
      .select($"structNew.guitarType")
      .show(false)

    /**
     * 5 TABLAS ONPRIMSE
     * MAESTRA
     * REF_TABLE_1 struct (col1_tb1, col2_tb1)
     * REF_TABLE_2 struct (col1_tb2, col2_tb2)
     * REF_TABLE_3 struct (col1_tb3, col2_tb3, col3_tb3, col4_tb3)  // REF_TABLE_3.col4_tb3
     * REF_TABLE_4 struct (col1_tb4)
     * REF_TABLE_5 struct (col1_tb5, struct(col1_tb5))
     */

    //    df.printSchema()
    //    df.show(false)

    //    val dfNew = df.
    //      groupBy("guitarType")
    //      .agg(collect_list(concat($"person", $"model")).as("structPersonAndModel"))
    //
    //    dfNew.printSchema()
    //
    //
    //    dfNew
    //      .show(false)

  }

}
