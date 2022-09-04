package org.datascience.spark.threads

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Test02Future {


  val future1: Future[String] = Future {
    Thread.sleep(200)
    "Clase del 02/09/2022"
  }

  def main(args: Array[String]): Unit = {


   Thread.sleep(400)

//     future1.onComplete {
//      case Success(v1)=> println(v1)
//    }

//    println(future1.value.get.get)

    val optionV: Option[Try[String]] = future1.value

    optionV.map(tried => {
      tried match {
        case Success(value) =>value
        case Failure(f) => f.getMessage
      }
    })
      .foreach(println)


  }

}
