name := "simple-transforming"

version := "1.0"
scalaVersion := "2.11.8" 
val akkaVersion = "2.5.13"
// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "org.apache.spark" %% "spark-sql" % "2.0.2" % "provided",
  "io.spray" %% "spray-json" % "1.3.5",
  "com.nrinaudo" %% "kantan.csv-generic" % "0.5.1",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.1.4" % Test,
  "javax.media" % "jai_core" % "1.1.3",
  "com.github.agourlay" %% "json-2-csv" % "0.2.3"
)
