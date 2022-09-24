package datascience.spark.streamingWithSocket

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SimpleStreamingSocket {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate();

    spark.sparkContext.setLogLevel("ERROR");


    import spark.implicits._

    val dfConnectSocker = spark
      .readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", "12345")
      .load()
      .select(split($"value", ",").as("data"))
      .select(
        $"data".getItem(0).as("dni"),
        $"data".getItem(1).as("fullName"),
        $"data".getItem(2).as("month")
      )

    dfConnectSocker.writeStream
      .format("console")
      .outputMode("append")
      .start()
      .awaitTermination() // IMPORTANTE

  }

}
