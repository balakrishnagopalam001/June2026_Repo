import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Demo5 {

  def main(args:Array[String]):Unit={

    System.setProperty("SPARK_LOCAL_IP", "127.0.0.1")
    System.setProperty("spark.driver.host", "127.0.0.1")

    val spark=SparkSession.builder()
      .appName("Demo5-Session")
      .master("local[1]")
      .getOrCreate()

    val df=spark.read
      .format("csv")
      .option("header","true")
      .option("path","D:/bala/dim_customers.csv")
      .load()
    // ------------- Using Select Columns and formatting the column with condition and applying Filter method
    df.select(
      col("customer_code"),
      col("customer"),
      col("channel"),
      when(col("channel")==="Direct",lit("Direct Marketing"))
        .otherwise(lit("Other_Channel"))
        .alias("Channel_Desc")
    ).filter(col("channel")=!="Direct").show(false)
  }

}
