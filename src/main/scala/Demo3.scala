import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Demo3 {

  def main(args :Array[String]):Unit ={

    System.setProperty("SPARK_LOCAL_IP", "127.0.0.1")
    System.setProperty("spark.driver.host", "127.0.0.1")

    val spark=SparkSession.builder()
      .appName("Demo3-SparkSession")
      .master("local[4]")
      .getOrCreate()

    val ddlschema = "product_code Int,division String,category String,product String,variant String"

    val df=spark.read
      .format("csv")
      .option("header","true")

      //---------Schema defining --------------
      //.option("infershema",true)  // To make the schema based on Data columns from source file
      .schema(ddlschema)            // Explicit Schema defined
      // --------Read modes -------------------------
      //      .option("mode","FAILFAST")
      //      .option("mode","PERMISSIVE") // KEEPS NULL TO THOSE RECORDS IF ANY DATA TYPE MISMATCH AS SPECIFIED IN ddlschema
      //      .option("mode","DROPMALFORMED") // REMOVES THE CORRUPTED RECORDS

      // --------File Path defined -------------------------
      .option("path","D:/Test_Data/project-de-fmcg-atlikon/0_data/1_parent_company/full_load/dim_products.csv")

      // --------Load the data -------------------------
      .load()
    // -------------------------------------------------
    df.filter(col("division")==="Archery").show()
    df.show(400)
    println("No.of Rows in file:"+df.count())

    spark.stop()

  } //End of main()

}
