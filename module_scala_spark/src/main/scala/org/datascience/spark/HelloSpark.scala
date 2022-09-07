package org.datascience.spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.log4j.{Level, Logger}

object HelloSpark {
//  Logger.getLogger("org").setLevel(Level.OFF)
//  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val conf= new org.apache.spark.SparkConf()
    conf.set("spark.executor.memory", "4g")
    conf.set("spark.driver.memory", "4g")
    conf.set("spark.cores.max", "2")
    conf.set("spark.driver.extraClassPath",
     """ driver_home+'/jdbc/postgresql-9.4-1201-jdbc41.jar:'\
        +driver_home+'/jdbc/clickhouse-jdbc-0.1.52.jar:'\
    +driver_home+'/mongo/mongo-spark-connector_2.11-2.2.3.jar:'\
    +driver_home+'/mongo/mongo-java-driver-3.8.0.jar'"""
    )

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
//      .config(conf)
      .getOrCreate();

//    spark.sparkContext.setLogLevel("ERROR");

    import spark.implicits._

    val nombre_cols = Array("instancesId", "segmentA")

    val dfMain: DataFrame = spark.sparkContext.parallelize(
      Seq(
        (10001, Seq("RECIBE_RECARGA_BITEL")),
        (10011, Seq("RECIBE_RECARGA_CLARO")),
        (10111, Seq("RECIBE_RECARGA_CLARO")),
        (10003, Seq("RECIBE_RECARGA_MOVISTAR")),
        (10002, Seq("RECIBE_RECARGA_ENTEL")))
    )
      .toDF(nombre_cols: _*)

    dfMain.printSchema()
    dfMain.show()

  }

}
