package week_two

object fixedpointSqrt {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(310); 
  def fixedPoint(f: Double => Double)(initGuess: Double)={
  	def iteration(guess: Double): Double={
  		val tolerance = 0.001
  		val nextGuess=f(guess)
  		if(nextGuess-guess < tolerance*guess) nextGuess
  		else iteration(nextGuess)
  	}
  	iteration(initGuess)
  };System.out.println("""fixedPoint: (f: Double => Double)(initGuess: Double)Double""");$skip(61); 
  def avgDamp(f: Double => Double)(x: Double) = (f(x) + x)/2;System.out.println("""avgDamp: (f: Double => Double)(x: Double)Double""");$skip(55); 
  def sqrt(x: Double)=fixedPoint(avgDamp(y=>x/y))(1.0);System.out.println("""sqrt: (x: Double)Double""");$skip(10); val res$0 = 
  sqrt(2);System.out.println("""res0: Double = """ + $show(res$0))}
}
