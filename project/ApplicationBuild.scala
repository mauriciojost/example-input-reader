import sbt.Keys._
import sbt._

object ApplicationBuild extends Build {

  object Versions {
    val spark = "1.6.0"
  }

  val projectName = "example-spark"

  val common = Seq(
    version := "1.0",
    organization := "http://mkuthan.github.io/",
    scalaVersion := "2.11.7"
  )

  val customScalacOptions = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xfuture",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused-import"
  )

  val customJavaInRuntimeOptions = Seq(
    "-Xmx512m"
  )

  val customJavaInTestOptions = Seq(
    "-Xmx512m"
  )

  val customLibraryDependencies = Seq(
    "org.apache.spark" %% "spark-core" % Versions.spark,
    "org.apache.spark" %% "spark-sql" % Versions.spark,
    "org.apache.spark" %% "spark-hive" % Versions.spark,
    "org.apache.spark" %% "spark-streaming" % Versions.spark,

    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",

    "org.slf4j" % "slf4j-api" % "1.7.10",
    "ch.qos.logback" % "logback-classic" % "1.1.2",

    "org.scalatest" %% "scalatest" % "2.2.4" % "test",

    "junit" % "junit" % "4.4" % "test"

  )

  val customExcludeDependencies = Seq(
    "org.slf4j" % "slf4j-log4j12"
  )

  lazy val main = Project(projectName, base = file("."))
    .settings(common)
    .settings(javaOptions in Runtime ++= customJavaInRuntimeOptions)
    .settings(javaOptions in Test ++= customJavaInTestOptions)
    .settings(scalacOptions ++= customScalacOptions)
    .settings(libraryDependencies ++= customLibraryDependencies)
    .settings(excludeDependencies ++= customExcludeDependencies)
    .settings(parallelExecution in Test := false)
    .settings(fork in Test := true)

}

