package org.datascience.spark.exercises

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Pregunta01 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  /**
   * ----------- SCHEMA ----------
   * root
   * |-- Acceleration: double (nullable = true)
   * |-- Cylinders: long (nullable = true)
   * |-- Displacement: double (nullable = true)
   * |-- Horsepower: long (nullable = true)
   * |-- Miles_per_Gallon: double (nullable = true)
   * |-- Name: string (nullable = true)
   * |-- Origin: string (nullable = true)
   * |-- Weight_in_lbs: long (nullable = true)
   * |-- Year: string (nullable = true)
   *
   *
   *
   *
   * 3. Agrupar y listar los carros que tengan el mismo HorsePower y renombrar las columnas obtenidas
   * con un nombre de facil entendimiento.
   *
   * Ejemplo
   * +------------+---------+------------+----------
   * |Horsepower|Name
   * +------------+---------+------------+----------
   * |130       | (chevrolet chevelle malibu, toyota ... )|
   * |150       | (plymouth satellite, amc rebel sst ...)|
   */
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Pregunta01")
      .getOrCreate()
    import spark.implicits._
    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read
      .json(PATH.CARS_PATH_URL)
    //
    //    df.printSchema()
    //    df.show(false)


    //    3. Agrupar y listar los carros que tengan el mismo HorsePower y renombrar las columnas obtenidas
    //      con un nombre de facil entendimiento.

    val dfNew = df
      .groupBy("HorsePower")
      .agg(collect_list(col("Name")))

    dfNew.printSchema()

    dfNew
      .show(false)

    //    1. Listar todos los autos que tengan menos o igual que 150 Cabellos de Fuerza(Horsepower)
    //    y que tenga menos 16 de Millas por galon (tiene que quitar los nullos)
    //    posterior tiene que renombrar los nombres a espaniol de las siguientes 5 columnas
    //      y solo mostrar las 5 columnas
    //
    //    Acceleration -> Aceleracion
    //    Cylinders -> Cilindros
    //    Horsepower -> Caballos_Fuerza
    //    Miles_per_Gallon -> Millas_Por_Galon
    //    Name -> Descripcion_Auto
    //

    //    df
    //      .where(
    //        $"Horsepower" < 150 &&
    //        ($"Miles_per_Gallon".isNotNull && $"Miles_per_Gallon" < 16)
    //      )
    //      .withColumn("Aceleracion",$"Acceleration")
    //      .select($"Acceleration".as("Aceleracion") , $"Cylinders".as("Cilindros"))
    //      .show(false)


    //    2. Agregar una columna adicional de nombre IS_USA y mostrar VERDAD  si el carro  pertenece a USA en caso contrario
    //      mostrar FALSO

    //    df
    //      .withColumn("IS_USA" ,
    //        when(
    //          $"Origin" === lit("USA") , "VERDAD"
    //        )
    //          .otherwise("FALSO")
    //      )
    //      .show(false)
  }

}
