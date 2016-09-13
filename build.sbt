name := "CRDT Kit"

val scala211 = "2.11.8"

lazy val root = project.in(file(".")).
  aggregate(crdtKitJVM, crdtKitJS)

lazy val crdtKit = crossProject.in(file(".")).
  settings(
    name := "crdt-kit",
    version := "1.0",
    scalaVersion := scala211,
    organization := "net.sigmalab",
    licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
    homepage := Some(url("https://github.com/schrepfler/crdt-kit")),
    publish := {},
    publishLocal := {},
    libraryDependencies ++= Seq(
      "io.jvm.uuid" %% "scala-uuid" % "0.2.1",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test",
      "org.specs2" %% "specs2-core" % "3.8.5" % "test",
      "org.typelevel" %% "cats-core" % "0.7.2"
    )
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    jsEnv := NodeJSEnv().value,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "0.7.2"
    )
  )

lazy val crdtKitJVM = crdtKit.jvm
lazy val crdtKitJS = crdtKit.js

// reactivate if needing to check which compiler flag is needed
//scalacOptions in ThisBuild ++= Seq("-feature")

