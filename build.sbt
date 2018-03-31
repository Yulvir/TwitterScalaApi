name := "scalaProject"

version := "0.1"

scalaVersion := "2.11.8"
sbtVersion := "0.13"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.1.0"
libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.1.0"

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % "1.1-M8",
  "io.spray" % "spray-http" % "1.1-M8",
  "io.spray" % "spray-routing" % "1.1-M8",
  "com.typesafe.akka" %% "akka-actor" % "2.4.20",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.9",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "net.liftweb" %% "lift-json" % "3.2.0",
  "ch.qos.logback" % "logback-classic" % "1.0.13"
)


resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

