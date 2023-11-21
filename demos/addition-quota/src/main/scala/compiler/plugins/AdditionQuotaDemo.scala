package compiler.plugins

@main def AdditionQuotaDemo(): Unit =
  // Any addition operations after the quota will cause errors

  // Commenting in the below will cause all further addition expressions to error
  // val addAll = 1 + 2 + 3 + 4 + 6 + 7

  // First five additions are fine
  val add1 = 1 + 1
  val add2 = 2 + 2
  val add3 = 3 + 3
  val add4 = 4 + 4
  val add5 = 5 + 5
  // Quota exceeded: Further addition operations error
  val add6 = 6 + 6