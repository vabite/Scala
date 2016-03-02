package week_one

object sqrt {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
	def abs(x:Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(130); 
	
	def sqrtIter(guess: Double, x: Double): Double =
	   if (isGoodEnough(guess, x)) guess
	   else sqrtIter(improve(guess, x), x);System.out.println("""sqrtIter: (guess: Double, x: Double)Double""");$skip(85); 
	
	def isGoodEnough(guess: Double, x: Double): Boolean =
		abs(guess*guess-x)<0.01*x;System.out.println("""isGoodEnough: (guess: Double, x: Double)Boolean""");$skip(71); 
	
	def improve(guess: Double, x: Double) =
	   (guess + x / guess) / 2;System.out.println("""improve: (guess: Double, x: Double)Double""");$skip(42); 
	
	def sqrt(x: Double) = sqrtIter(1.0, x);System.out.println("""sqrt: (x: Double)Double""");$skip(14); val res$0 = 
	
	sqrt(1e60);System.out.println("""res0: Double = """ + $show(res$0))}
}
