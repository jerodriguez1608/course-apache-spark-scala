package org.datascience.spark

object trabajo_investigacion {

  def main(args: Array[String]): Unit = {

    /***
     * Tarea:
     *
     * investigar 3 nuevas funciones aplicadas para un String en Scala
    */

    /***
     * 1. primera funcion
     * compareTo(String)
     *
     * funcion compateTo(String another String)
     * permite comparar dos variables del tipo String letra por letra
     * y obtener como resultado el numero de letra no coincidente (en valores negativos)
     */

    val world1="kenyi"
    val world2="kenyiqwew"

    println(world1.compareTo(world2))

    /***
     * 2. segunda funcion
     *
     * hashCode()
     * funcion que permite obtener el codigo hash de un String
     */

    val world3 = "kenyiqwew"

    println(world3.hashCode())

    /** *
     * 3. tercera funcion
     *
     * subSequence(int start, int end)
     * funcion que retorna una subsecuencia de un String,
     * indicado valores de inicio y fin
     */

    val world4 = "kenyi"

    println(world4.subSequence(1,3))


  }

}
