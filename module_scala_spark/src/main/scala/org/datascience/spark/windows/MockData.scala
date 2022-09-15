package org.datascience.spark.windows

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

object MockData {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();

  def mockDataTable2(): DataFrame = {

    import spark.implicits._

    Seq(
      // WINDOWS KEY = ID , ORDER = TS
      // "2", "Jhon", "165040692999"
      ("2", "IOS", "10-10-2020", null),
      ("3", "ANDROID", "10-10-2020", "14-10-2020"),
      ("4", "HUAWEI", "10-10-2020", null)
    ).toDF("id", "os", "dateCreation", "dateUpdate")


  }

  def mockData(): DataFrame = {

    import spark.implicits._

    Seq(
      // WINDOWS KEY = ID , ORDER = TS
      // "2", "Jhon", "165040692999"
      ("2", "Jhon", "165040692999"),
      ("2", "John", "1650406925"),

      // "3", "Jhon", "165040694999"
      ("3", "Jean", "165040694999"),
      ("3", "Gean", "165040693999"),
      ("3", "Yan", "1650406926"),

      // "3", "Michael", "1650406926"
      ("4", "Michelle", "1650406924"),
      ("4", "Michael", "1650406926"),

      // "5", "Jisus", "1650406928"
      ("5", "Jesus", "1650406927"),
      ("5", "Jisus", "1650406928"),

      ("6", "Kelly", "1650406933")
    ).toDF("id", "nameUpdate", "ts")


  }
}
