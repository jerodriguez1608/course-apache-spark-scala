package org.datascience.spark.exercises

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Pregunta02 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  /**
  ----------- SCHEMA ----------
   root
     |-- hometown: string (nullable = true)
     |-- id: long (nullable = true)
     |-- name: string (nullable = true)
     |-- year: long (nullable = true)

      +-----------+---+------------------+----+
    |hometown   |id |name              |year|
    +-----------+---+------------------+----+
    |Sydney     |1  |AC/DC             |1973|
    |Sydney     |5  |PVT               |1986|
    |London     |0  |Led Zeppelin      |1968|
    |London     |6  |The Rolling Stones|1968|
    |London     |7  |The Clash         |1968|
    |Los Angeles|3  |Metallica         |1981|
    |Los Angeles|8  |Weezer            |1993|
    |Los Angeles|9  |Bad Religion      |1979|
    |Liverpool  |4  |The Beatles       |1960|
    +-----------+---+------------------+----+

   1. Retornar cuantas bandas hay por ciudad(hometown)

   Output Dataframe::
    Lima | 3
    Lima | 4

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
      .appName("Pregunta02")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read
      .json("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_scala_spark\\src\\main\\resources\\data\\bands\\bands.json")

    df.printSchema()
    df.show(false)


  }

}
