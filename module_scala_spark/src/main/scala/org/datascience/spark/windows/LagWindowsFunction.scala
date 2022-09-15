package org.datascience.spark.windows

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lead}
import org.datascience.spark.windows.MockData._

object LagWindowsFunction {


  def main(args: Array[String]): Unit = {

    val dfMock =  mockData()
    import spark.implicits._

    dfMock.printSchema()

    dfMock
      .show(false)

    val windowsOrderTsById = Window.partitionBy("id").orderBy($"ts")

  val dfRes =   dfMock
      .withColumn("fLag", lead("ts", 1).over(windowsOrderTsById))
      .orderBy("id", "ts")

    dfRes.show(false)




  }
}
