import Dependencies.*

lazy val arithmeticQuotaDemo = project
  .in(file("."))
  .settings(
    name := "arithmetic-quota-demo",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

    autoCompilerPlugins := true,
    addCompilerPlugin("compiler.plugins" %% "arithmetic-quota-plugin" % "0.0.1-SNAPSHOT"),

    publish / skip := true
  )
