package org.datascience.spark.readSink

import InitSpark._

object ReadPostgress {


  def main(args: Array[String]): Unit = {

      val dfPostgress =
        spark
          .read
          .format("jdbc")
          .option("url","jdbc:postgresql://localhost:5432/postgres")
          .option("dbtable","public.phonebook")
          .option("user","postgres")
          .option("password","postgres")
          .load()

    dfPostgress.printSchema()

    dfPostgress.show(false)

  }


}
