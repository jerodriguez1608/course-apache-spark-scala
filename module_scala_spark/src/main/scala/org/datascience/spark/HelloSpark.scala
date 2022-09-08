package org.datascience.spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.log4j.{Level, Logger}

object HelloSpark {
//  Logger.getLogger("org").setLevel(Level.OFF)
//  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate();

//    spark.sparkContext.setLogLevel("ERROR");

    import spark.implicits._

    val nombre_cols = Array("instancesId", "segmentA")

    val dfMain = spark.sparkContext.parallelize(
      Seq(
        (10001, Seq("RECIBE_RECARGA_BITEL")),
        (10011, Seq("RECIBE_RECARGA_CLARO")),
        (10111, Seq("RECIBE_RECARGA_CLARO")),
        (10003, Seq("RECIBE_RECARGA_MOVISTAR")),
        (10002, Seq("RECIBE_RECARGA_ENTEL")))
    )
      .toDF(nombre_cols: _*)

    dfMain.printSchema()
    dfMain.show(false)

  }

}
