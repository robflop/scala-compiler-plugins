import Dependencies.*

ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / sbtPlugin := false
ThisBuild / organization := "compiler.plugins"

lazy val scalaCompilerPlugins = project
  .in(file("."))
  .aggregate(
    divisionByZeroPlugin,
    sayHiPlugin,
    additionQuotaPlugin
  )
  .settings(
    name := "scala-compiler-plugins",
    scalaVersion := scala3Version,
    publish / skip := true
  )

lazy val divisionByZeroPlugin = project.in(file("plugins/division-by-zero"))
lazy val sayHiPlugin = project.in(file("plugins/say-hi"))
lazy val additionQuotaPlugin = project.in(file("plugins/addition-quota"))

lazy val divisionByZeroDemo = project.in(file("demos/division-by-zero"))
lazy val sayHiDemo = project.in(file("demos/say-hi"))
lazy val additionQuotaDemo = project.in(file("demos/addition-quota"))

lazy val publishPluginsLocal = taskKey[Unit]("Publish plugins locally")
publishPluginsLocal := {
  Command.process("clean", state.value)
  Command.process("compile", state.value)
  Command.process("package", state.value)
  Command.process("publishLocal", state.value)
}