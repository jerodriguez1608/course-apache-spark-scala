# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from pyspark.sql import SparkSession
from pyspark.sql import DataFrame
from pyspark.sql.functions import *
from pyspark.sql.types import *
from pyspark.sql.window import *


def runInit(name):
    # Use a breakpoint in the code line below to debug your script.
    return SparkSession \
        .builder \
        .master("local[1]") \
        .appName(name) \
        .getOrCreate()


def readFile(spark: SparkSession, path: str):
    return spark.read \
        .format("json") \
        .load(path)


pathGuitar = "C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_python_pyspark\\data\\guitars\\guitars.json"
pathBands = "C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_python_pyspark\\data\\bands\\bands.json"

spark: SparkSession = runInit("ApplyFunctionWindows")

dfGuitar = readFile(spark, pathGuitar)
dfBands = readFile(spark, pathBands)

dfGuitar.printSchema()
dfGuitar.show(truncate=False)

dfBands.printSchema()
dfBands.show(truncate=False)

dfJoinBetweenBandsAndGuitar = dfGuitar.alias("g") \
    .join(dfBands.alias("b"), col("g.bandId") == col("b.id"), "inner")\
    .selectExpr("b.name", "g.*")


dfJoinBetweenBandsAndGuitar.show(truncate=False)
