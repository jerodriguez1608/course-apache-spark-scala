package org.datascience.spark.dataframes

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructType}
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.functions._

object DataframeInit04 {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("DataFrameInit01")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._ // ES IMPORTANTE , NO OLVIDAR


    val data = Seq(
      Row("James", List(
        Row("Scala", "XA", 300)
//        ,
//        Row("Python", "DA", 300)
      )),
      Row("Jhon", List(
        Row("Scala", "XA", 300),
        Row("Java", "JVM", 150)
      ))
    )

    val schema =
      new StructType()
        .add("name", StringType)
        .add("languagePromming",
          ArrayType(
            new StructType()
              .add("language", StringType)
              .add("runtime", StringType)
              .add("years", IntegerType)
          )
        )

    val rdd: RDD[Row] = spark.sparkContext.parallelize(data)


    val dataframe = spark.createDataFrame(rdd, schema)

    dataframe.printSchema()

    dataframe
      .show(false )

    dataframe
      .select($"languagePromming"(0).as("structIn"))
      .select($"structIn.years".as("years"))
//      .withColumn("name_language", $"structIn.language")
//      .withColumn("newcolumn", col("structIn.language"))
      .where($"years" === 300)
      .show(false )

  }


}
