package datascience.spark.casosPracticos.caso01

import datascience.spark.casosPracticos.GlobalPath

object ReadFromDirJsonToPostgresPublic extends  GlobalPath {

  def main(args: Array[String]): Unit = {

    val mapConnection: Map[String, String] = Map(
      "url" -> "jdbc:postgresql://localhost:3322/postgres",
      "user" -> "admin",
      "password" -> "12345",
      "dbTable" -> "public.tb_lateral"
    )

    val template = TemplateWritePostgresFromDirJson(PATH_JSON_MOVIMIENTOS,
                                                    PATH_JSON_MOVIMIENTOS_CHECKPOINT,
                                                    mapConnection)

    template.runStreaming()


  }




}
