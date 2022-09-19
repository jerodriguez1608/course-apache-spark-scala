package org.datascience.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SparkInit {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();

  spark.sparkContext.setLogLevel("ERROR");
  spark.sparkContext.setLogLevel("ERROR");

  import spark.implicits._

}
