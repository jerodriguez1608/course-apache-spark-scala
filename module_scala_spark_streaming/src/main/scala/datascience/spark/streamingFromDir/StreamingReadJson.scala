package datascience.spark.streamingFromDir

import org.apache.log4j.{Level, Logger}
import org.apache.parquet.format.IntType
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object StreamingReadJson {


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
        StructField("calories", IntegerType, true)
      )
    )

    val streamingReadJson = spark
      .readStream
      .schema(schema)
      .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark_streaming\\src\\main\\scala\\datascience\\spark\\streamingFromDir\\origen")

    streamingReadJson.printSchema()

    streamingReadJson.writeStream
      .format("console")
      .outputMode("append")
      .start()
      .awaitTermination()

  }

}
