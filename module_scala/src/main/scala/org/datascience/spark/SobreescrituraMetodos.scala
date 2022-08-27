package org.datascience.spark

class ProgrammingLanguage(comeOff: Int) {

  def sumMore100(): Int = {
    comeOff + 100
  }

  def show() = {
    println(s"Soy ProgrammingLanguage y tengo $comeOff")
  }
}

case class Python(comeOff: Int) extends ProgrammingLanguage(comeOff) {
  override def show() = {
    println(s"Soy Python y tengo $comeOff")
  }
}

object SobreescrituraMetodos {

  def main(args: Array[String]): Unit = {

    val progg = new ProgrammingLanguage(100)
    progg.show()
    println(progg.sumMore100())

    val python = Python(100)
    python.show()
    println(python.sumMore100())
  }
}
