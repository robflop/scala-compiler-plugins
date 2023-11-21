import Dependencies.*

lazy val sayHiDemo = project
  .in(file("."))
  .settings(
    name := "say-hi-demo",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

    autoCompilerPlugins := true,
    addCompilerPlugin("compiler.plugins" %% "say-hi-plugin" % "0.0.1-SNAPSHOT"),

    publish / skip := true
  )
