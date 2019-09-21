name := "book_scala"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/com.github.scala-incubator.io/scala-io-core
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.3-1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3-1",
  // https://mvnrepository.com/artifact/com.lambdaworks/jacks
  "com.lambdaworks" %% "jacks" % "2.5.2"
)
