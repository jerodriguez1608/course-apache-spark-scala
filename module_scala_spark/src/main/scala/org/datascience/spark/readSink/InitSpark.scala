package org.datascience.spark.readSink

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object InitSpark {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();

  import spark.implicits._
}
