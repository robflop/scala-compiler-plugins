import Dependencies.*

lazy val sayHiPlugin = project
  .in(file("."))
  .settings(
    name := "say-hi-plugin",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= List(scala3Compiler)
  )
