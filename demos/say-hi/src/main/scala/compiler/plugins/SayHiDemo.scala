package compiler.plugins

@main def SayHiDemo(): Unit =
  val hi: String = "Hi, compiler!" // Caught by ValDef transform
  var hi2 = "Hi, compiler!" // Caught by ValDef transform (vars are ValDefs with mutable flag)
  hi2 = "Hi, compiler!" // Caught by Assign transform
  println(hi)