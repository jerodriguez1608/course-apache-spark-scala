package org.datascience.spark.practica02

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Ejercicio2 {
  def main(args: Array[String]): Unit = {
    fnEjercicio12
  }

  def fnEjercicio1 = {
    val products = DataDemo.mockProducts().filter(product => product.category.startsWith("Pelota") && product.price  > 100)
    products.foreach(println)
  }

  def fnEjercicio2: Array[Order]={
    val orders: Array[Order] = DataDemo.mockOrders()
      .filter((order: Order) => order.products.filter((product: Product) => product.category.startsWith("Ropa")).length > 0)
    orders.foreach(println)
    orders
  }

  def fnEjercicio3 = {
    val products = DataDemo.mockProducts().filter(_.category.startsWith("Accesorios")).map((p: Product) => {
      p.price = p.price * 0.9
      p
    })
    products.foreach(println)
  }


  def fnEjercicio4 = {
    val order = DataDemo.mockOrders()
      .filter((o : Order) =>
        o.orderDate.isAfter(LocalDate.of(2021, 4, 1))
          && o.orderDate.isBefore(LocalDate.of(2022, 2,1))
          && (o.customer.nivel == "Plata" || o.customer.nivel == "Bronce"))
    order.foreach(println)
  }

  def fnEjercicio5 = {
    val min = DataDemo.mockProducts().filter(product => product.category.startsWith("Accesorios")).min(Ordering.by((p: Product) => (p.price)))
    println(min)
  }

  def fnEjercicio6 = {
    val min = DataDemo.mockOrders().sortWith((a, b) => a.orderDate.isAfter(b.orderDate))
      .foreach(println)
  }

  def fnEjercicio7 = {
    DataDemo.mockOrders().filter((o: Order) =>
      o.orderDate.isAfter(LocalDate.of(2022, 5, 18))
      && o.orderDate.isBefore(LocalDate.of(2022, 5,30)))
      .sortWith((a, b) => a.orderDate.isAfter(b.orderDate))
      .map((o: Order) => {
        println(o)
        o
      })
  }

  def fnEjercicio8 = {
    val result = DataDemo.mockOrders().filter((o: Order) =>
      o.orderDate.isAfter(LocalDate.of(2022, 3, 1))
        && o.orderDate.isBefore(LocalDate.of(2022, 3,31)))
      .map((o: Order) => o.products.map(_.price).sum).sum
    println(result)
  }

  def fnEjercicio9 ={
    val newMap: collection.mutable.Map[Int, Int] = collection.mutable.Map()
    DataDemo.mockOrders().map((o: Order) => {
      newMap.put(o.id, o.products.length)
    })
    newMap.foreach(println)
  }

  def fnEjercicio10 = {
    val result = DataDemo.mockOrders().map((o: Order) => o).groupBy(_.customer.id)
    println(result)
  }

  def fnEjercicio11 = {
    val newMap: collection.mutable.Map[Int, Double] = collection.mutable.Map()
    DataDemo.mockOrders().map((o: Order) => {
      newMap.put(o.id, o.products.map(_.price).sum)
    })
    println(newMap)
  }

  def fnEjercicio12 = {
    val newMap: collection.mutable.Map[String, Double] = collection.mutable.Map()
    DataDemo.mockProducts()
      .groupBy((p: Product) => p.category)
      .map( e => newMap.put(e._1, e._2.map(_.price).max))
    println(newMap)
  }
}
