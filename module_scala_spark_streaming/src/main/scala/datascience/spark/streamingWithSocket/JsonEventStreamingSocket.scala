package datascience.spark.streamingWithSocket

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object JsonEventStreamingSocket {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate();

    spark.sparkContext.setLogLevel("ERROR");

    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "2")
    spark.sqlContext.setConf("spark.default.parallelism", "2")

    import spark.implicits._

    val schema =  StructType(
      List(
        StructField("dni", StringType, true),
        StructField("ts", LongType, true)
      )
    )

    //{"dni":"454322324","ts":1663986967}
    val dfConnectSocker = spark
      .readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", "12345")
      .load()
      .withColumn("parsed", from_json($"value", schema))
      .select($"parsed.dni",to_timestamp($"parsed.ts").as("timestamp"))
      .groupBy($"dni")
      .agg(
        count(lit(1)),
        max("timestamp")
      )

    dfConnectSocker.writeStream
      .format("console")
      .outputMode("complete")
      .start()
      .awaitTermination() // IMPORTANTE

  }

}
