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

class SayHiPlugin extends StandardPlugin:
  val name: String = "sayHi"
  val description: String = "Greets the programmer back"

  override def init(options: List[String]): List[PluginPhase] =
    (new SayHiPhase) :: Nil
end SayHiPlugin

class SayHiPhase extends PluginPhase:
  import tpd.*

  val phaseName: String = "sayHi"

  override val runsAfter: Set[String] = Set(Pickler.name)
  override val runsBefore: Set[String] = Set(Staging.name)

  override def transformValDef(tree: ValDef)(using ctx: Context): Tree =
    tree match
      case ValDef(_, tpt, Literal(Constant("Hi, compiler!"))) if tpt.tpe <:< defn.StringType =>
        report.warning("Hi, programmer!", tree.sourcePos)
      case _ =>
        ()
    tree

  override def transformAssign(tree: Assign)(using ctx: Context): Tree =
    tree match
      case Assign(lhs, Literal(Constant("Hi, compiler!"))) if lhs.tpe <:< defn.StringType =>
        report.warning("Hi, programmer!", tree.sourcePos)
      case _ =>
        ()
    tree
end SayHiPhase