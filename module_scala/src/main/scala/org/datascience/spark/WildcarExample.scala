package org.datascience.spark

object WildcarExample {

  def main(args: Array[String]): Unit = {

    def wildcardExample(num: Int, names: String*): Seq[String] = {
      names
    }

    println(wildcardExample(100))
    println(wildcardExample(100,"Jhon"))
    println(wildcardExample(100,"Jhon", "Elmer", "Kelly"))
  }
}
