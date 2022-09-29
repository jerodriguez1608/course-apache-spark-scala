package datascience.spark.casosPracticos.caso01

import datascience.spark.casosPracticos.{GlobalPath, Schemas, SparkInit}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object ReadFromDirJsonToPostgres extends  GlobalPath {

  def main(args: Array[String]): Unit = {

    val mapConnection: Map[String, String] = Map(
      "url" -> "jdbc:postgresql://localhost:5432/postgres",
      "user" -> "postgres",
      "password" -> "postgres",
      "dbTable" -> "public.tb_movimientos"
    )

    val template = TemplateWritePostgresFromDirJson(PATH_JSON_MOVIMIENTOS,
                                                    PATH_JSON_MOVIMIENTOS_CHECKPOINT,
                                                    mapConnection)

    template.runStreaming()


  }




}
