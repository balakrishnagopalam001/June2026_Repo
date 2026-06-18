import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object June15_Col {
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
      .option("path", "D:/Bala/details-2026-04-05.csv")
      //.option("inferschema","true")
      .schema(pgschema)
      .option("mode","DROPMALFORM") // FAILFAST , DROPMALFORM
      .load()

//    df.select(
//      col("id").alias("Employee ID"),
//      col("name").alias("Employee Name"),
//      col("salary").alias("Employee Salary"),
//      col("city").alias("Employee City"),
//      when(
//        col("salary")>100, "Rich"
//      ).otherwise ("Poor").alias("Income")
//    ).show()

    spark.sql(
      """
        |select
        |  id,
        |  name,
        |  salary,
        |  city,
        |case
        |  when (salary >100)
        |  then 'Rich'
        |  else 'Below Poverty'
        |end as Status
        |""".stripMargin
    ).show()

    df.printSchema()

  }
}