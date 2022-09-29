package datascience.spark.casosPracticos.caso01

import datascience.spark.casosPracticos.{Schemas, SparkInit}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger

case class TemplateWritePostgresFromDirJson(pathOrigen: String,
                                            pathCheckPoint: String,
                                            mapConnection: Map[String, String])
  extends SparkInit with Schemas {

  def runStreaming() = {

    val dfOrigenStreaming = readStreamingJson()

    dfOrigenStreaming
      .writeStream
      .outputMode("append")
      .option("checkpointLocation", pathCheckPoint)
      .foreachBatch(writePostgresql)
      .trigger(Trigger.Once())
      .start()
      .awaitTermination()
  }

  val writePostgresql = (dataMicroBatch: DataFrame, id: Long) => {
    val url = mapConnection("url")
    val user = mapConnection("user")
    val password = mapConnection("password")
    val dbTable = mapConnection("dbTable")

    dataMicroBatch
      .write
      .format("jdbc")
      .option("url", url)
      .option("dbtable", dbTable)
      .option("user", user)
      .option("password", password)
      .mode("append")
      .save()
  }

  def readStreamingJson(): DataFrame = {

    spark
      .readStream
      .schema(schemaJsonMovimientos)
      .json(pathOrigen)

  }
}
