package week_four

class Expr{
	def show: String = this match {
  	case Sum(e1, e2) => show(e1)+"+"+show(e2)
  	case Number(n) => n.toString
	}
}
		
case class Sum(val e1: Number, val e2: Number) extends Expr
	
case class Number(val n: Int) extends Expr

new Sum(new Number(2), new Number(1)).show()
  