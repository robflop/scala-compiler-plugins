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

class AdditionQuotaPlugin extends StandardPlugin:
  val name: String = "additionQuota"
  val description: String = "Puts a quota on the number of addition signs that may be used in code"

  override def init(options: List[String]): List[PluginPhase] =
    (new AdditionQuotaPhase(5)) :: Nil
end AdditionQuotaPlugin

class AdditionQuotaPhase(quota: Int) extends PluginPhase:
  import tpd.*
  var count = 0;

  val phaseName: String = "additionQuota"

  override val runsAfter: Set[String] = Set(Pickler.name)
  override val runsBefore: Set[String] = Set(Staging.name)

  override def transformApply(tree: Apply)(using ctx: Context): Tree =
    tree match
      case Apply(Select(_, nme.ADD), _) =>
        count = count + 1
        if count > quota then report.error(s"You may only use a total of $quota addition signs!", tree.sourcePos)
      case _ =>
        ()
    tree
end AdditionQuotaPhase