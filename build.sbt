import Dependencies.*

ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / sbtPlugin := false
ThisBuild / organization := "compiler.plugins"

lazy val scalaCompilerPlugins = project
  .in(file("."))
  .aggregate(
    divisionByZeroPlugin
  )
  .settings(
    name := "scala-compiler-plugins",
    scalaVersion := scala3Version,
    publish / skip := true
  )

lazy val divisionByZeroPlugin = project.in(file("plugins/division-by-zero"))
lazy val divisionByZeroDemo = project.in(file("demos/division-by-zero"))

lazy val publishPluginsLocal = taskKey[Unit]("Publish plugins locally")
publishPluginsLocal := {
  Command.process("clean", state.value)
  Command.process("compile", state.value)
  Command.process("package", state.value)
  Command.process("publishLocal", state.value)
}