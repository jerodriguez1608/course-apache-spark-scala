package org.datascience.spark.windows

import MockData._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.row_number

object LastRowsWindowsFunction {


  def main(args: Array[String]): Unit = {

    val dfMock =  mockData()
    import spark.implicits._

    dfMock.printSchema()

    dfMock
      .withColumn("time_stamp", $"ts".cast("timestamp"))
      .show(false)

    val windowsOrderTsById = Window.partitionBy("id").orderBy($"ts".desc)

  val dfRes =   dfMock
      .withColumn("rowNumber", row_number.over(windowsOrderTsById))
      .orderBy("id", "ts")

    dfRes.show(false)

    dfRes.
      where($"rowNumber" === 1)
      .show(false)


  }
}
