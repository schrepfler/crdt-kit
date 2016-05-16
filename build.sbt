name := "crdt-kit"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.jvm.uuid" %% "scala-uuid" % "0.2.1",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.specs2" %% "specs2-core" % "3.8.2" % "test",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.5",
  "org.spire-math" %% "algebra" % "0.4.2",
  "org.typelevel" %% "cats" % "0.5.0"
)

// reactivate if needing to check which compiler flag is needed
//scalacOptions in ThisBuild ++= Seq("-feature")

