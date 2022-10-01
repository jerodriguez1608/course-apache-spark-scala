package datascience.spark.casosPracticos.caso02

import datascience.spark.casosPracticos.{GlobalPath, SparkInit}
import io.delta.tables.DeltaTable

object CreateTableDelta extends SparkInit with GlobalPath {

  def main(args: Array[String]): Unit = {

//   createTableDelta()

    DeltaTable.forPath(PATH_DELTA_CALORIAS).toDF.show(100)
  }


  def createTableDelta() = {
    import spark.implicits._

    val tablaC = Seq(
      ("48842843", 100, "1664419229")
    )
      .toDF("dni", "calorias", "ts")

    tablaC
      .write
      .format("delta")
      .save(PATH_DELTA_CALORIAS)
  }

}
