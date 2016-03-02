package week_two

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(63); 
  val x=new Rational(2, 6);System.out.println("""x  : week_two#35.Rational#842810 = """ + $show(x ));$skip(27); 
  val y=new Rational(5, 7);System.out.println("""y  : week_two#35.Rational#842810 = """ + $show(y ));$skip(27); 
  var z=new Rational(3, 2);System.out.println("""z  : week_two#35.Rational#842810 = """ + $show(z ));$skip(18); val res$0 = 
  x.sub(y).sub(z);System.out.println("""res0: week_two#35.Rational#842810 = """ + $show(res$0))}
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
