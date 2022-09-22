# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from pyspark.sql import SparkSession
from pyspark.sql import DataFrame
from pyspark.sql.functions import *
from pyspark.sql.types import *


def runInit(name):
    # Use a breakpoint in the code line below to debug your script.
    return SparkSession \
        .builder \
        .master("local[1]") \
        .appName(name) \
        .getOrCreate()


def readFile(spark: SparkSession):
    return spark.read \
        .format("json") \
        .load(
        "C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_python_pyspark\\data\guitars\\guitars.json")


spark: SparkSession = runInit("ReadFileJsonClasePython")

df = readFile(spark)

df.printSchema()
df.show(truncate=False)
