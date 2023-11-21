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

class ArithmeticQuotaPlugin extends StandardPlugin:
  val name: String = "arithmeticQuota"
  val description: String = "Puts a quota on the number of arithmetic signs that may be used in code"

  override def init(options: List[String]): List[PluginPhase] =
    (new ArithmeticQuotaPhase(5)) :: Nil
end ArithmeticQuotaPlugin

class ArithmeticQuotaPhase(quota: Int) extends PluginPhase:
  import tpd.*
  var count = 0;

  val phaseName: String = "arithmeticQuota"

  override val runsAfter: Set[String] = Set(Pickler.name)
  override val runsBefore: Set[String] = Set(Staging.name)

  override def transformApply(tree: Apply)(using ctx: Context): Tree =
    tree match
      case Apply(Select(_, nme.ADD), List(Literal(Constant(rhsVal: Int)))) =>
        val newCount: Int = if rhsVal != 0 then count + 1 else count
        count = newCount
      case Apply(Select(_, nme.MUL), List(Literal(Constant(rhsVal: Int)))) =>
        val newCount: Int = if rhsVal != 1 then count + rhsVal else count
        count = newCount
      case Apply(Select(_, nme.SUB), List(Literal(Constant(rhsVal: Int)))) =>
        val newCount: Int = if rhsVal != 0 then count - 1 else count
        count = if newCount > 0 then newCount else 0
      case Apply(Select(_, nme.DIV), List(Literal(Constant(rhsVal: Int)))) =>
        val newCount: Int = count - rhsVal
        count = if newCount > 0 then newCount else 0
      case _ =>
        ()

    if count > quota then report.error(s"You may only use a total of $quota arithmetic signs!", tree.sourcePos)
    tree
end ArithmeticQuotaPhase