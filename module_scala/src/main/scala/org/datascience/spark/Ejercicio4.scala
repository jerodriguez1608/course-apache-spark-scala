package org.datascience.spark

// pairs common String

object Ejercicio4 {
  def main(args: Array[String]): Unit = {

    val q = List(("hello","world"), ("hi","world"))
//    val q = List(("papa","arroz"), ("escualido","arana"),("lio","rama"),("beetroot","sandals"),("qwerty","yelmo"))

    def twoStrings(s1: String, s2: String): String = {
      // declarative testing
      var result = "NO"
      if (s1.length <= s2.length){
        val setS1 = s1.split("").toSet
        for (s <- setS1){
          if (s2.contains(s)){
            result = "YES"
            result
          }
        }
      }else{
        val setS2 = s2.split("").toSet
        for (s <- setS2) {
          if (s1.contains(s)) {
            result = "YES"
            result
          }
        }
      }
      result
    }

    for (qIter <- q){
      val s1 = qIter._1
      val s2 = qIter._2

      val result = twoStrings(s1, s2)
      println(result)
    }
  }
}
