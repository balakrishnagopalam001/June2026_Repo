import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType ,StructType,StructField}

object June14_Schema {
  def main(args:Array[String]):Unit = {

//    System.setProperty("SPARK_LOCAL_IP", "127.0.0.1")
//    System.setProperty("spark.driver.host", "127.0.0.1")

    val spark = SparkSession.builder()
      .appName("Spark DataFrames")
      .master("local[1]")
      .getOrCreate()

    val pgschema = StructType(List(
      StructField("id", IntegerType, nullable = true),
      StructField("name", StringType, nullable = true),
      StructField("salary", IntegerType, nullable = true),
      StructField("city", StringType, nullable = true)
    )
    )

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .option("path", "D:\\Bala\\details-2026-04-05.csv")
      //.option("inferschema","true")
      .schema(pgschema)
      .option("mode","DROPMALFORM") // FAILFAST , DROPMALFORM
      .load()

    df.show()

    df.printSchema()

  }
}
