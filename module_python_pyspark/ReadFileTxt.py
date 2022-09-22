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


def readFile(spark: SparkSession, schema: StructType):
    return spark.read \
        .format("csv") \
        .option("delimiter", ",") \
        .schema(schema) \
        .load("C:\\workspace\\scala\\science_research\\course-apache-spark-scala\\module_python_pyspark\\data\\mock.txt")


spark: SparkSession = runInit("ReadFileTxtClasePython")

schema = StructType(
    [
        StructField("code", IntegerType(), True),
        StructField("nombre", StringType(), True),
        StructField("date", DateType(), True),
        StructField("numberCard", StringType(), True)
    ]
)

df = readFile(spark, schema)

df.printSchema()
df.show(truncate=False)
