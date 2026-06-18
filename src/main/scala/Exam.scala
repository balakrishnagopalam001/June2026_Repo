import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}


object Exam {

  def category_status(df:DataFrame):DataFrame={
    df.select(
        col("id"),
        col("name"),
        col("salary"),
        col("city"),
        when(col("salary")>250,lit("Eligible"))
          .otherwise(lit("Not-Eligible")).alias("Qualify_status")
    )
  }

  def main(args:Array[String]):Unit={

    val spark=SparkSession.builder()
      .appName("BG_Exam")
      .master("local[*]")
      .getOrCreate()

    val df=spark.read
      .format("csv")
      .option("header","true")
      .option("path","D:\\Test_Data\\details.csv")
      .load()


    df.printSchema()

//    category_status(df).show()

    import spark.implicits._

    val data = List(
      ("2026-06-20","2026-05-05","12:05:00"),
      ("",null,"02:05:00"),
      (null,"2023-01-05","10:55:00")
    ).toDF("date_str1","date_str2","time_str")

    val vdate = data.select(
      date_format(col("date_str1"),"yyyy.mm.dd")
      ,dayofmonth(col("date_str1"))
      ,month(col("date_str1"))
      ,dayofyear(col("date_str1"))
      ,year(col("date_str1"))
      ,when(
         col("date_str1").isNull,current_date()
       ).when(col("date_str1") ==="",current_date()
      ).otherwise(
         col("date_str1")
      ).alias("Date_First")
      ,coalesce(col("date_str2"),lit("31-12-2026")).alias("Date_Second")
    ).show(false)

    val ydate=data.withColumnRenamed(
      "date_str1","Date_First"
    ).show(false)

//    println(df.columns(0))

//    for (i <- df.schema.fields.indices) {
//      println(df.schema.fields(i))
//    }

//    df.select(
//      col("id"),
//      col("name"),
//      col("salary"),
//      col("city"),
//      when(col("salary")>250,lit("Eligible"))
//       .otherwise(lit("Not-Eligible")).alias("Qualify_status")
//    ).show(false)


//// --------- [Program to check the given number is Even or Odd ] ------
//    println("Enter a number to check it is Even or Odd")
//    val a = readInt()
//    if(a%2==0)
//      println("Entered number ["+a+"] is Even number")
//    else
//      println("Entered number ["+a+"] is Odd number")
//
//// --------------------------------------------------------------------
//// ------ [Program to print the given number in Reverse order ] -------
//
//    println("Enter a number to Print it in reverse order")
//    val b = readInt()
//    val revb = b.toString.reverse
//    println("Entered value is ["+b+"]")
//    println("Reverse of the given number is ["+revb+"]")
//
//// --------------------------------------------------------------------
//// ------ [Program to check the given number is a Prime number ] -------
//
//    println("Enter a number to check it is Prime Number")
//    val valb = readInt()
////    if (valb >1)
////      for (i <-2  i<= valb ))
//
//    println("Given number ["+valb+"] is not a Prime Number")
//    println("Given number ["+valb+"] is a Prime Number")
//    println("Given number ["+valb+"] is not a Prime Number")

// --------------------------------------------------------------------

  }
}
