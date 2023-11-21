import Dependencies.*

lazy val arithmeticQuotaPlugin = project
  .in(file("."))
  .settings(
    name := "arithmetic-quota-plugin",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= List(scala3Compiler)
  )
