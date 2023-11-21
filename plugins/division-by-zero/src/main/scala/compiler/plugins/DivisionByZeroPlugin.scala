package compiler.plugins

import dotty.tools.dotc.plugins.{PluginPhase, StandardPlugin}
abstract class DivisionByZeroPlugin extends StandardPlugin:
  val name: String = "divisionByZero"
  override val description: String = "Division by zero check"

abstract class DivisionByZeroPhase extends PluginPhase:
  val phaseName: String = "divisionByZero"