import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object June14 {
  def main(args:Array[String]):Unit = {

//    System.setProperty("SPARK_LOCAL_IP", "127.0.0.1")
//    System.setProperty("spark.driver.host", "127.0.0.1")

   val spark=SparkSession.builder()
          .appName("Spark DataFrames")
          .master("local[1]")
          .getOrCreate()

    val df=spark.read
        .format("csv")
        .option("header","true")
        .option("path","D:/Test_Data/project-de-fmcg-atlikon/0_data/1_parent_company/full_load/dim_products.csv")
        .load()


    df.show()

    df.printSchema()

  }

}
