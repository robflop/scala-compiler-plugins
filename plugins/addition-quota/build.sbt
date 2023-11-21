import Dependencies.*

lazy val additionQuotaPlugin = project
  .in(file("."))
  .settings(
    name := "addition-quota-plugin",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= List(scala3Compiler)
  )
