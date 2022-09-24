package org.datascience.spark.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SimpleTransformRDD {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {


    val spark =  SparkSession.builder()
      .master("local[1]")
      .appName("SimpleTransformRDD")
      .getOrCreate();

    val rddWords = spark
      .read
      .csv("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\daily.txt")
      .rdd

    rddWords
      .map(row => row.getString(0).split(" "))
      .flatMap(rowArr => rowArr)
      .filter(word => ! Seq("-",",",".","?").contains(word))
      .groupBy(word => word)
      .map(groupWord => (groupWord._1, groupWord._2.size) )
      .take(3)
      .foreach(println)


  }

}
