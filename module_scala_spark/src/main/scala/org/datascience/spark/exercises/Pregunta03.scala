package org.datascience.spark.exercises

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Pregunta03 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  /**
  ----------- SCHEMA ----------
     root
   |-- guitarType: string (nullable = true)
   |-- id: long (nullable = true)
   |-- person: string (nullable = true)
   |-- model: string (nullable = true)

    +----------------------+---+-------+------------+
    |guitarType            |id |person   |model       |
    +----------------------+---+-------+------------+
    |Electric double-necked|0  |Gibson |EDS-1275    |
    |Electric double-necked|10 |John   |EDS-1344    |
    |Electric              |5  |Fender |Stratocaster|
    |Electric              |1  |Gibson |SG          |
    |Acoustic              |2  |Taylor |914         |
    |Acoustic              |3  |Ishanna|980         |
    |Electric              |3  |ESP    |M-II        |
    +----------------------+---+-------+------------+

   1. Mostrar un dataframe con todas las personas que tengan una guitarra de tipo Electric o sus derivados
   2. Agrupar por tipo de guitarra y mostrar cuantas personas tiene ese tipo de guitarra,
    Ejemplo:
    Eletric , 3
    ...
   3. Agrupar por tipo de guitarra y mostrar  las persona y el modelo de guitarra que tienen , ejemplo:
   +----------------------+---+-------+------------+
    |guitarType            |person_model|
    +----------------------+---+-------+------------+
    |Electric double-necked| (Gibson, EDS-1275), (John,EDS-1344) |
    |Electric              | (Fender, Stratocaster , ...)|
    ...

   Output Dataframe:: Lima | 3 Lima | 4

   2. Retornar las bandas con mas de 50 anios de antiguedad
      y renombrar sus columnas a espaniol

   3. Agregar una columna adicional de nombre NEWCOLUMN_1 y retornar VERDAD o MENTIRA si
      la banda tiene mas de 10 letras

  3. Agregar una columna adicional de nombre NEWCOLUMN_2 y retornar NUEVA o ANTIGUA O MUY ANTIGUA si
      la banda  tiene menos de menos 40,  menos 50 o mas 50 anios respectivamente

   */
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("Pregunta03")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read
      .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\guitars\\guitars.json")

    df.printSchema()
    df.show(false)


  }

}
