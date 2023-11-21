package compiler.plugins

import dotty.tools.dotc.ast.Trees.*
import dotty.tools.dotc.ast.tpd
import dotty.tools.dotc.core.Constants.Constant
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.StdNames.*
import dotty.tools.dotc.core.Symbols.*
import dotty.tools.dotc.plugins.{PluginPhase, StandardPlugin}
import dotty.tools.dotc.report
import dotty.tools.dotc.transform.{Pickler, Staging}

class DivisionByZeroPlugin extends StandardPlugin:
  val name: String = "divisionByZero"
  val description: String = "Division by zero check"

  override def init(options: List[String]): List[PluginPhase] =
    (new DivisionByZeroPhase) :: Nil
end DivisionByZeroPlugin

class DivisionByZeroPhase extends PluginPhase:
  import tpd.*

  val phaseName: String = "divisionByZero"

  override val runsAfter: Set[String] = Set(Pickler.name)
  override val runsBefore: Set[String] = Set(Staging.name)

  override def transformApply(tree: Apply)(implicit ctx: Context): Tree =
    tree match
      case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) if rcvr.tpe <:< defn.IntType =>
          report.error("Illegal division: Divide by zero", tree.srcPos)
      case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) if rcvr.tpe <:< defn.FloatType =>
        report.error("Illegal division: Divide by zero", tree.srcPos)
      case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) if rcvr.tpe <:< defn.DoubleType =>
        report.error("Illegal division: Divide by zero", tree.srcPos)
      case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) if rcvr.tpe <:< defn.LongType =>
        report.error("Illegal division: Divide by zero", tree.srcPos)
      case _ =>
        ()
    tree
end DivisionByZeroPhase