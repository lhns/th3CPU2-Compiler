package de.lolhens.th3cpu2.compiler

class Main {

}

object Main {

  sealed abstract class Reg(val addr: Int)

  object Reg {

    sealed abstract class Src(addr: Int) extends Reg(addr)

    object Src {

      case object ALL extends Src(0)

      case object ALR extends Src(1)

      case object ALRes extends Src(2)

      case object Const extends Src(7)

    }

    sealed abstract class Dst(addr: Int) extends Reg(addr)

    object Dst {

      case object ALL extends Dst(0)

      case object ALR extends Dst(1)

    }

  }

  sealed abstract class Op(val code: Int)

  object Op {

    case object MulHigh extends Op(0)

    case object MulLow extends Op(1)

    case object Add extends Op(2)

    case object Sub extends Op(3)

    case object Nand extends Op(4)

    case object Xor extends Op(5)

    case object Div extends Op(6)

    case object Mod extends Op(7)

  }

}
