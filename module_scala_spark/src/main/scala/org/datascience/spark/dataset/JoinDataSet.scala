package org.datascience.spark.dataset

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

case class Guitar(id: BigInt, model: String, make: String, guitarType: String, idBanda: BigInt)
case class OutputJoin(nameBand: String, guitarType: String, model: String)

object JoinDataSet {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SimpleTransformData")
      .getOrCreate();

    import spark.implicits._

    val dataSetBands =
      spark
        .read
        .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\bands\\bands.json")
        .as[Banda]

    val dataSetGuitars =
      spark
        .read
        .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\guitars\\guitars.json")
        .as[Guitar]

    dataSetBands.show(false)
    dataSetGuitars.show(false)

    dataSetGuitars.as("g")
      .joinWith(dataSetBands.as("b"),
        $"g.idBanda" === $"b.id"
      )
      .map(joinRow => {
        val guitarEntity: Guitar = joinRow._1
        val bandEntity: Banda = joinRow._2

        OutputJoin(bandEntity.name, guitarEntity.guitarType, guitarEntity.model)

      })
      .show(false)

  }


}
