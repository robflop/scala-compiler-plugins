import Dependencies.*

lazy val divisionByZeroDemo = project
  .in(file("."))
  .settings(
    name := "division-by-zero-demo",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

    autoCompilerPlugins := true,
    addCompilerPlugin("compiler.plugins" %% "division-by-zero-plugin" % "0.0.1-SNAPSHOT"),

    publish / skip := true
  )
