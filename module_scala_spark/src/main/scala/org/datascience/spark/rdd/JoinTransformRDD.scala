package org.datascience.spark.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD

import scala.collection

case class Alimento(alimento: String, codeGroup: Int, price : Double)
case class Catalog(code: Int, desc: String)

object JoinTransformRDD {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark =  SparkSession.builder()
      .master("local[1]")
      .appName("JoinTransformRDD")
      .getOrCreate();

    /**
     * Alimentos RDD
     * papa , 1000 , 9.2
     *
     * Catalogo RDD
     * 1000 , Tuberculos
     *
     * Resultado
     * el precio de la papa es 9.2 es un Tuberculo
     */

    val rddCatalogos: RDD[Catalog] = spark.sparkContext.parallelize(
      Seq(
        Catalog(10001, "Tuberculos"),
        Catalog(10002, "Carnes"),
        Catalog(10003, "Mariscos"),
        Catalog(10004, "Cereales"),
        Catalog(10005, "Menestras"),
      )
    )

    val rddAlimentos : RDD[Alimento] = spark.sparkContext.parallelize(
      Seq(
        Alimento("papa", 10001, 9.2),
        Alimento("camote", 10001, 11.2),
        Alimento("yuca", 10001, 8.2),
        Alimento("pollo", 10002, 15.2),
        Alimento("carne", 10002, 28.0),
        Alimento("conejo", 10002, 33.0),
        Alimento("pescado", 10003, 11.2),
        Alimento("pota", 10003, 5.2),
        Alimento("arroz", 10004, 8.3),
        Alimento("quinua", 10004, 6.4),
        Alimento("lentejas", 10005, 6.4),
        Alimento("frejoles", 10005, 7.7),
        Alimento("pallares", 10005, 9.4),
        Alimento("no existe", 10008, 9.4)
      )
    )


    /**
     * ESTRUCTURA DE UN RDD PARA TRABAJAR CON UN JOIN, GROUPBYKEY, REDUCEBYKEY
     *
     * TUPLA (PARTE A , PARTE)
     * (KEY, RDD)
     */

    println("********************************************************")
    println("JOIN IN RDDs")
    println("********************************************************")

    val rddTupleAlimento = rddAlimentos.map(rdd => (rdd.codeGroup, rdd))
    val rddTupleCatalog= rddCatalogos.map(rdd => (rdd.code, rdd))

    rddTupleAlimento
      .leftOuterJoin(rddTupleCatalog)
      .map(resJoin => {
        //el precio de la papa es 9.2 es un Tuberculo
        val alimento: Alimento = resJoin._2._1
        val descCatlogo: String = resJoin._2._2.map(_.desc).getOrElse("No identificado")

        s" el precio de la/el ${alimento.alimento}  es ${alimento.price} es un ${descCatlogo}"
      })
      .foreach(println)

    println("********************************************************")
    println("REDUCE BY KEY")
    println("********************************************************")

    val rddTupleToReduceKey = rddAlimentos.map(rdd => (rdd.codeGroup, rdd))

    /**
     * (10002,76.2)
        (10001,28.599999999999998)
        (10008,9.4)
        (10003,16.4)
        (10005,23.5)
        (10004,14.700000000000001)

        (CARNES,76.2)
        (PESCADOS,28.599999999999998) ...
     */
    rddTupleToReduceKey
      .map(al => (al._1, al._2.price))
      .reduceByKey(_ + _)
      .collect
      .foldLeft(
        Seq[(String, Double)]()
      ) {
        (seq, tuple) => {

          val listCatalogPossible = rddCatalogos.collect()
            .filter(_.code.toString == tuple._1.toString)
            .map(_.desc)

          val descriptionCatalog = if(listCatalogPossible.nonEmpty)  listCatalogPossible.head else "NaN"

          val out : Seq[(String, Double)] = Seq((descriptionCatalog, tuple._2))

          out ++ seq

        }
      }
      .foreach(println)
    }

}
