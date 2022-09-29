package datascience.spark.casosPracticos.caso02

import datascience.spark.casosPracticos.{GlobalPath, SparkInit}
import org.apache.spark.sql.functions.{from_json, to_timestamp}
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType}

object StreamingFromSocketToDelta extends SparkInit with GlobalPath{


  def main(args: Array[String]): Unit = {

    import spark.implicits._

    val schema =  StructType(
      List(
        StructField("dni", StringType, true),
        StructField("calorias", IntegerType, true),
        StructField("ts", StringType, true)
      )
    )
    /**
     * Ruta = C:\netcat-1.11
     * CMD 1 == nc -l -p 8000
     */
    //{"dni":"454322324","calorias":100,"ts":1663986967}
    spark
      .readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", "8000")
      .load()
      .withColumn("parsed", from_json($"value", schema))
      .selectExpr("parsed.*")
      .writeStream
      .option("checkpointLocation",PATH_DELTA_CALORIAS_CHECKPOINT )
      .format("delta")
      .outputMode("append")
      .start(PATH_DELTA_CALORIAS)
      .awaitTermination()

  }

}
