package org.datascience.spark.readSink

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.datascience.spark.windows.MockData.spark

object ReadJson {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate();

    import spark.implicits._

    val schema =
      new StructType()
        .add("hometown", StringType)
        .add("id", IntegerType)
        .add("name", StringType)
        .add("year", IntegerType)

    val dfSink: DataFrame =
      spark
        .read
        .schema(schema)
        .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\bands")

    //    println(dfSink.)

    dfSink.show(false)

  }

}
