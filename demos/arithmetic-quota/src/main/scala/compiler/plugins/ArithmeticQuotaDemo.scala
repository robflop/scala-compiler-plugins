package compiler.plugins

@main def ArithmeticQuotaDemo(): Unit =
  // Any addition operations after the quota will cause errors

  // Multiplications count as RHS additions, so commenting in the below
  // will cause all further additions to error (unless offset by subtractions / divisions)
  // val mult1 = 1 * 5
  // val div1 = 1 / 5;

  // First five additions are fine
  val add1 = 1 + 1
  val add2 = 2 + 2
  val add3 = 3 + 3
  val add4 = 4 + 4
  val add5 = 5 + 5

  // Additions by zero are free of charge
  val add0 = 0 + 0

  // Subtractions count as -1 additions, so commenting the below in will get rid of below error
  // val sub1 = 1 - 1

  // Subtractions by zero don't free up a slot
  val sub0 = 0 - 0

  // Quota exceeded: Further addition operations error
  val add6 = 6 + 6