import Dependencies.*

lazy val divisionByZeroPlugin = project
  .in(file("."))
  .settings(
    name := "division-by-zero-plugin",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= List(scala3Compiler)
  )
