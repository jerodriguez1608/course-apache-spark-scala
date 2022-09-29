package datascience.spark.casosPracticos

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

trait Schemas {

  val schemaJsonMovimientos = StructType(
    List(
      StructField("dni", StringType, true),
      StructField("monto", StringType, true),
      StructField("tipo", StringType, true)
    )
  )



}
