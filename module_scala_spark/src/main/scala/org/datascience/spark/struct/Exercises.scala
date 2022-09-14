package org.datascience.spark.struct

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object Exercises {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("DataFrameInit01")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._ // ES IMPORTANTE , NO OLVIDAR

    val dfSecundario = spark.sparkContext.parallelize(
      Seq(
        (10001, "Lima", "Ate", 1),
        (10002, "Ica", "Molina", 2),
        (10003, "Trujillo", "Surco", 3),
        (10004, "Cusco", "Victoria", 4)
      )
    ).toDF("code", "Ciudad", "distrito", "idPerson")

    val dfMain = spark.sparkContext.parallelize(
      Seq(
        (1, "Jhon", "7659333"),
        (2, "Kelly", "4443434"),
        (3, "Miguel", "434334"),
        (4, "Jose", "433434")
      )
    ).toDF("idPerson", "nombre", "dni")

    /**
     * TABLA RESULTANTE: PERSONAS_DISTRITO
     *
     * fecha: Date
     * persona: struct<id, nombre , dni>
     * ubicacion: struct<code, ciudad, distrito>
     *
     */

    val joinOn = Seq("idPerson")

   val dfResultante =  dfMain.as("m")
      .join(dfSecundario.as("s"), joinOn)
      .select("m.idPerson", "m.nombre" , "m.dni", "s.code", "s.ciudad" ,"s.distrito")
      .withColumn("persona", struct("idPerson","nombre","dni"))
      .withColumn("ubicacion", struct("code","ciudad","distrito"))
      .withColumn("fecha", current_timestamp())
      .select("fecha", "persona","ubicacion")

    dfResultante.printSchema()

    dfResultante.show(false)

    dfResultante
      .select($"persona.nombre", $"ubicacion.distrito")
      .show(false)


  }
}
