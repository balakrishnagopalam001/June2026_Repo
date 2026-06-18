scalaVersion in ThisBuild := "2.11.12"

lazy val root = (project in file("."))
  .settings(
    name := "Dev_01"
  )

// Source: https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4"