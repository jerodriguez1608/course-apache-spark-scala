package org.datascience.spark.windows

import MockData._
import org.apache.spark.sql.functions._

object FunctionStruct {

  import spark.implicits._

  def main(args: Array[String]): Unit = {

    val df =
      mockDataTable2()
//        .withColumn("dateCreation", $"dateCreation".cast("date" ))
        .withColumn("dateCreation",to_date($"dateCreation", "dd-MM-yyyy"))
        .withColumn("dateUpdate",to_date($"dateUpdate", "dd-MM-yyyy"))

    df.printSchema()

    df.show(false)


    val dfRes =
      df

        .withColumn("dateFinal",
          when($"dateUpdate".isNull, $"dateCreation")
            .otherwise($"dateUpdate")
        )

    dfRes.show(false)

  }


}
