package org.datascience.spark.readSink

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import InitSpark._

object ReadTxt {

  def main(args: Array[String]): Unit = {

    val schema =
      new StructType()
        .add("code", IntegerType)
        .add("name", StringType)
        .add("date", DateType)
        .add("numberCard", StringType)

    val dfSink: DataFrame =
      spark
        .read
        .format("csv")
        .option("delimiter", ",")
        .schema(schema)
        .load("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\mockSinExt")
        .sample(0.1) //  must be on interval [0, 1]

    println(dfSink.count())

//
//    dfSink.printSchema()
//
//    dfSink.show(false)
//
//    val udfFunctionCypher = udf(
//      (numberCard: String) => {
//        /**
//         * AQUI IMPLEMENTAR EL METODO DE CIFRADO:
//         * KEY VAULT -- SPRINT SECURITY
//         * AES256 --- IV KEY 8, KEY 32
//         */
//
//        numberCard.hashCode
//      }
//    )
//
//
//    dfSink
//      .withColumn("encryptNumberCard", udfFunctionCypher(col("numberCard")))
//      .show(false)
  }

}
