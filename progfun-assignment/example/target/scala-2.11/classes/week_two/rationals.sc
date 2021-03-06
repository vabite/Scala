package week_two

object rationals {
  val x=new Rational(2, 6)                        //> x  : week_two#35.Rational#842810 = 1/3
  val y=new Rational(5, 7)                        //> y  : week_two#35.Rational#842810 = 5/7
  var z=new Rational(3, 2)                        //> z  : week_two#35.Rational#842810 = 3/2
  x.sub(y).sub(z)                                 //> res0: week_two#35.Rational#842810 = -79/42
}

class Rational(x: Int, y: Int){
	private def gcd(a: Int, b: Int):Int=if(b==0) a else gcd(b, a%b)
	val g=gcd(x, y)
	val numer=x
	val denom=y
	
	def neg=new Rational(-numer, denom)
	def add(that: Rational)=
		new Rational(
			numer*that.denom + that.numer * denom,
			denom * that.denom
		)
	def sub(that: Rational)=add(that.neg)
	override def toString=numer/g+"/"+denom/g
}