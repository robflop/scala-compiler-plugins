# scala-compiler-plugins

### Usage

This is a scala 3 sbt project. Its structure is adapted from https://github.com/mattmoore/scala-compiler-plugins.

To start, compile and publish the compiler plugins locally via `sbt publishPluginsLocal` (or as `publishPluginsLocal` on the shell). This is a custom command invoking the `clean`, `compile`, `package` and `publishLocal` commands successively.

Afterward, compiling the given examples (via e.g. `sbt divisionByZeroDemo/clean divisionByZeroDemo/compile`) should result in the respective plugin effects.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

-----

<details>
<summary>Division by zero plugin (StandardPlugin)</summary>
When this plugin is activated, dividing by zero will throw a compiler error.
</details>