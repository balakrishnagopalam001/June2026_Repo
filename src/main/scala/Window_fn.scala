import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._

object Window_fn {

  def main(args:Array[String]):Unit={

   val spark= SparkSession.builder()
     .appName("WindowFunctions")
     .master("local[1]")
     .getOrCreate()

   val df=spark.read
     .format("csv")
     .option("header","true")
     .option("path","D:\\Bala\\details-2026-04-05.csv")
     .load()

    df.printSchema()

    val window=Window.orderBy(col("id")).rowsBetween(Window.unboundedPreceding,Window.currentRow)

    val ds=df.select(
     col("id"),
     col("name"),
     col("salary"),
     sum(col("salary")).over(window).alias("Sum_Fn_unbounded")
    )

    println(ds.show(50,false))

//    ---------------End of Main -------------
  spark.close()
  }

}
