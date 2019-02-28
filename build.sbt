name := "CRDT Kit"

val scala212 = "2.12.3"
val scala211 = "2.11.11"

lazy val root = project.in(file(".")).
  aggregate(crdtKitJVM, crdtKitJS)

lazy val crdtKit = crossProject.in(file(".")).
  settings(
    name := "crdt-kit",
    version := "1.0",
    scalaVersion := scala212,
    organization := "net.sigmalab",
    licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
    homepage := Some(url("https://github.com/schrepfler/crdt-kit")),
    publish := {},
    publishLocal := {},
    libraryDependencies ++= Seq(
      "io.jvm.uuid" %% "scala-uuid" % "0.3.0",
      "org.scalatest" %% "scalatest" % "3.0.6" % "test",
      "org.specs2" %% "specs2-core" % "4.3.5" % "test",
      "org.typelevel" %% "cats-core" % "1.6.0"
    ),
//    ensimeIgnoreScalaMismatch in ThisBuild := true
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "1.6.0"
    )
  )

lazy val crdtKitJVM = crdtKit.jvm
lazy val crdtKitJS = crdtKit.js

// reactivate if needing to check which compiler flag is needed
//scalacOptions in ThisBuild ++= Seq("-feature")
