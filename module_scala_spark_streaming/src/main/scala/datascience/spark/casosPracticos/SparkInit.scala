package datascience.spark.casosPracticos

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

trait SparkInit {


  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();

  spark.sparkContext.setLogLevel("ERROR");

  spark.sqlContext.setConf("spark.sql.shuffle.partitions", "2")
  spark.sqlContext.setConf("spark.default.parallelism", "2")

  spark.sqlContext.setConf("spark.sql.shuffle.partitions", "2")
  spark.sqlContext.setConf("spark.default.parallelism", "2")

}
