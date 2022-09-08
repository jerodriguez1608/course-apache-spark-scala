package org.datascience.spark.dataframes

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object DataframeInit02 {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("DataFrameInit01")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._ // ES IMPORTANTE , NO OLVIDAR


    val columns = Seq("code", "description", "partitions")

    val dataframe  = spark.sparkContext.parallelize(
      Seq(
        (10001, "Lima", Seq(1,2,3,4)),
        (10002, "Ica", Seq(1,2,3,4)),
        (10003, "Trujillo", Seq(1,2,3,4)),
        (10004, "Cusco", Seq(1,2,3,4))
      )
    ).toDF(columns: _*)

    dataframe.printSchema()

    dataframe.show()

  }


}
