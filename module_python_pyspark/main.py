# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from pyspark.sql import SparkSession
from pyspark.sql import DataFrame
from pyspark.sql.functions import *

# def runInit():
#     # Use a breakpoint in the code line below to debug your script.
#     return SparkSession \
#         .builder \
#         .master("local[1]") \
#         .appName("DataFrame01") \
#         .getOrCreate()
#
#
# def createDataFrame(spark):
#     return spark.createDataFrame(
#         [(1, "foo"), (2, "letter"), (3, "test"), (4, "test")],
#         ["id", "word"]
#     )


spark: SparkSession = SparkSession \
        .builder \
        .master("local[1]") \
        .appName("DataFrame01") \
        .getOrCreate()

df: DataFrame = spark.createDataFrame(
        [(1, "foo"), (2, "letter"), (3, "test"), (4, "test")],
        ["id", "word"]
    )

df \
    .withColumn("newColumnA", concat_ws("|||", lit("CONSTANTE_A"), col("id"))) \
    .withColumn("newColumnB", concat_ws("|||", lit("CONSTANTE_B"), col("id"))) \
    .where(col("id") < 3) \
    .select("word", "newColumnA", "newColumnB") \
    .show(truncate=False)


