package org.datascience.spark

object A {
  def metodoA() {}
  val UNIT = "Unit"
}

import A._


object ImportMethodAndParameters {
  def main(args: Array[String]): Unit = {

    println(UNIT)
    println(metodoA())
  }
}
