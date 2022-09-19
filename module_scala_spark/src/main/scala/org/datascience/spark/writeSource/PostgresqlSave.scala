package org.datascience.spark.writeSource

import org.datascience.spark.SparkInit._

object PostgresqlSave {


  def main(args: Array[String]): Unit = {

    import spark.implicits._

    val dfOrigen =  spark
      .read
      .format("jdbc")
      .option("url","jdbc:postgresql://localhost:5432/postgres")
      .option("dbtable","public.phonebook")
      .option("user","postgres")
      .option("password","postgres")
      .load()

    val dfNuevo =
      Seq(
        ("+1 334 456 4433", "Liam", "Bacilio", "Tarapoto"),
        ("+1 445 222 3333", "Kelly", "Aguirre", "Ica")
      )
        .toDF("phone", "firstname", "lastname", "address")

    val dfResultado = dfOrigen.as("m")
      .join(dfNuevo.as("in"), Seq("firstname"), "outer")
//      .selectExpr("m.firstname","in.*")

    dfResultado.show(false)

//
//    df
//      .write
//      .format("jdbc")
//      .option("url", "jdbc:postgresql://localhost:5432/postgres")
//      .option("dbtable", "public.phonebook")
//      .option("user", "postgres")
//      .option("password", "postgres")
//
//      //UPDATE NO FUNCIONA PARA SPARK WRITE
//
//           .mode("overwrite")
//
//      /**
//       * AGREGAR HACIA ABAJO , NUEVO REGISTRO
//       */
////      .mode("append")
//      .save()


  }

}
