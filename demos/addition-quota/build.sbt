import Dependencies.*

lazy val additionQuotaDemo = project
  .in(file("."))
  .settings(
    name := "addition-quota-demo",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,

    autoCompilerPlugins := true,
    addCompilerPlugin("compiler.plugins" %% "addition-quota-plugin" % "0.0.1-SNAPSHOT"),

    publish / skip := true
  )
