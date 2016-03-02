package week_four

object pattern {

	class Expr{
		def show(e: Expr): String = this match {
  		case Sum(e1, e2) => show(e1)+"+"+show(e2)
  		case Number(n) => n.toString
		}
	}
		
	case class Sum(val e1: Number, val e2: Number) extends Expr
	
	case class Number(val n: Int) extends Expr
  
}
