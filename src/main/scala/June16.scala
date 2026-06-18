import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object June16 {
  def main(args:Array[String]):Unit={

      val spark = SparkSession.builder()
        .appName("Spark DataFrames")
        .master("local[1]")
        .getOrCreate()

  import spark.implicits._

      val d1= List((1,"Hello"),(2," "),(3,"World")).toDF("col1","col2")
      d1.select(concat_ws("_",col("col1"),col("col2")).alias("Concat_ws")).show(false)

      val d2=List(("hello world this is sample text")).toDF("col1")
      d2.select(initcap(col("col1")).alias("Initcap")).show(false)

      val d3=List(("welcome Orange county")).toDF("col1")
      d3.select(split(col("col1"),"").as("split_col")).show(false)
  }
}
