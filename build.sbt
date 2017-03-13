name := """scala-slick-application1"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.typesafe.slick" %% "slick" % "3.2.0",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "org.postgresql" % "postgresql" % "9.4.1212",
    "mysql" % "mysql-connector-java" % "5.1.34"
  )


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

