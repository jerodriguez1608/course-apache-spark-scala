package datascience.spark.casosPracticos.caso02

import datascience.spark.casosPracticos.{GlobalPath, SparkInit}
import org.apache.spark.sql.{DataFrame, ForeachWriter, Row}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger


class PostgreOpeEachRowToWrite(mapConnection: Map[String, String]) extends ForeachWriter[Row] {

  var driver = "org.postgresql.Driver"
  var connection: java.sql.Connection = _
  var statement: java.sql.PreparedStatement = _

  var i_sql = "insert into public.tb_calorias (dni, totalcalorias, ts) values (?, ?, ?)"
  var u_sql = "update  public.tb_calorias set totalcalorias = ? , ts = ? where dni = ?"

  // OPEN CONNECTION
  override def open(partitionId: Long, epochId: Long): Boolean = {
    Class.forName(driver)
    connection = java.sql.DriverManager.getConnection(mapConnection("url"),
      mapConnection("user"),
      mapConnection("password"))

    connection.setAutoCommit(false)

    true
  }

  //LOGIC WORKING
  override def process(row: Row): Unit = {


    val isUpdate = row.getAs[Boolean]("isUpdate")
    println(s"isUpdate:: ${isUpdate} , dni:: ${row.getAs[String]("dni")}")

    if (!isUpdate) {
      statement = connection.prepareStatement(i_sql)
      statement.setString(1, row(0).toString)
      statement.setString(2, row.getAs[String]("totalcalorias"))
      statement.setString(3, row(2).toString)
    } else {
      statement = connection.prepareStatement(u_sql)
      statement.setString(1, row.getAs[String]("totalcalorias"))
      statement.setString(2, row(2).toString)
      statement.setString(3, row(0).toString)
    }

    statement.executeUpdate()

  }

  //CLOSE CONNECTION
  override def close(errorOrNull: Throwable): Unit = {
    connection.commit()
    connection.close()
  }

}


object StreamingFromDeltaToPostgres extends SparkInit with GlobalPath {
  val mapConnection: Map[String, String] = Map(
    "url" -> "jdbc:postgresql://localhost:5432/postgres",
    "user" -> "postgres",
    "password" -> "postgres",
    "dbTable" -> "public.tb_calorias"
  )

  def main(args: Array[String]): Unit = {

    import spark.implicits._

    val tbDeltaStreaming = spark
      .readStream
      .format("delta")
      .option("ignoreChanges","true")
      .option("ignoreChanges","true")
      .load(PATH_DELTA_CALORIAS)


    val dfStreamingSumarCalorias =
      tbDeltaStreaming
        //        .withWatermark("","")
//        .select($"dni", $"calorias".cast("string").as("totalcalorias"), $"ts")

    val writePostgresl = new PostgreOpeEachRowToWrite(mapConnection)

    dfStreamingSumarCalorias
      .transform((microBatch: DataFrame) => {

        val dfGroupBy = microBatch
//          .withWatermark("ts","10 sec")
//          .groupBy(window(""),"dni")
//          .agg(
//            sum($"calorias").cast("string").as("totalcalorias"),
//            max($"ts").as("ts")
//          )
          .select($"dni", $"calorias".cast("string").as("totalcalorias"), $"ts")

        val tableBatchCaloriasMain = readBatchPostgresql() //.cache()

        val df = dfGroupBy.as("in")
          .join(tableBatchCaloriasMain.as("m"), $"in.dni" === $"m.dni", "left")
          .withColumn("isUpdate",
            when($"m.dni".isNotNull, true)
              .otherwise(false)
          )
          .withColumn("resultado",
            when($"m.dni".isNotNull, $"m.totalcalorias".as("int"))
              .otherwise($"in.totalcalorias")
          )
          .select($"in.dni", $"resultado".cast("string").as("totalcalorias"), $"in.ts", $"isUpdate")

        df
      })
      .writeStream
      .outputMode("update")
//      .option("checkpointLocation", PATH_TB_MAESTRA_CHECKPOINT)
      .foreach(writePostgresl)
//        .foreachBatch(writePostgresql)
      //      .trigger(Trigger.ProcessingTime("10 seconds"))
      .start()
      .awaitTermination()

  }

  def readBatchPostgresql(): DataFrame = {

    spark
      .read
      .format("jdbc")
      .option("url", "jdbc:postgresql://localhost:5432/postgres")
      .option("dbtable", "public.tb_calorias")
      .option("user", "postgres")
      .option("password", "postgres")
      .load()

  }

  val writePostgresql = (dataMicroBatch: DataFrame, id: Long) => {


    import spark.implicits._

    val mapConnection: Map[String, String] = Map(
      "url" -> "jdbc:postgresql://localhost:5432/postgres",
      "user" -> "postgres",
      "password" -> "postgres",
      "dbTable" -> "public.tb_calorias"
    )

    val url = mapConnection("url")
    val user = mapConnection("user")
    val password = mapConnection("password")
    val dbTable = mapConnection("dbTable")


    val dfGroupBy = dataMicroBatch
      .agg(
        sum($"calorias").cast("string").as("totalcalorias"),
        max($"ts").as("ts")
      )
      .select($"dni", $"totalcalorias", $"ts")

    val tableBatchCaloriasMain = readBatchPostgresql() //.cache()

    val df1 = dfGroupBy.as("in")
      .join(tableBatchCaloriasMain.as("m"), $"in.dni" === $"m.dni", "left")
      .withColumn("isUpdate",
        when($"m.dni".isNotNull, true)
          .otherwise(false)
      )
      .withColumn("resultado",
        when($"m.dni".isNotNull, $"in.totalcalorias" + $"m.totalcalorias".as("int"))
          .otherwise($"in.totalcalorias")
      )
      .select($"in.dni", $"resultado".cast("string").as("totalcalorias"), $"in.ts", $"isUpdate")

    val df2 = dfGroupBy.as("in")
      .join(tableBatchCaloriasMain.as("m"), $"in.dni" === $"m.dni", "antiOuter")
      .withColumn("isUpdate",
        when($"m.dni".isNotNull, true)
          .otherwise(false)
      )
      .withColumn("resultado",
        when($"m.dni".isNotNull, $"in.totalcalorias" + $"m.totalcalorias".as("int"))
          .otherwise($"in.totalcalorias")
      )
      .select($"in.dni", $"resultado".cast("string").as("totalcalorias"), $"in.ts", $"isUpdate")

    df1
      .union(df2)
      .write
      .format("jdbc")
      .option("url", url)
      .option("dbtable", dbTable)
      .option("user", user)
      .option("password", password)
      .mode("overwrite")
      .save()
  }

}
