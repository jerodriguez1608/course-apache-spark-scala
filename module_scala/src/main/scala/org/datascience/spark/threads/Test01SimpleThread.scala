package org.datascience.spark.threads

object Test01SimpleThread {

  val ASSIGN_THREAD = 0
  def main(args: Array[String]): Unit = {

    val thread = new Thread{
      override def run (): Unit = {
        Thread.sleep(3000)
        println("Hello Thread")

      }
    }

    thread.start()

    println("Prueba 1 ")
    println("Prueba 2 ")
    println("Prueba 3")

    for(vv <- 1 to 10) {
      Thread.sleep(800)
      println(s"Nummm :: $vv")
    }

  }

}
