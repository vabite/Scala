package week_one

object sqrt {
	def abs(x:Double) = if (x < 0) -x else x  //> abs: (x: Double)Double
	
	def sqrtIter(guess: Double, x: Double): Double =
	   if (isGoodEnough(guess, x)) guess
	   else sqrtIter(improve(guess, x), x)    //> sqrtIter: (guess: Double, x: Double)Double
	
	def isGoodEnough(guess: Double, x: Double): Boolean =
		abs(guess*guess-x)<0.01*x         //> isGoodEnough: (guess: Double, x: Double)Boolean
	
	def improve(guess: Double, x: Double) =
	   (guess + x / guess) / 2                //> improve: (guess: Double, x: Double)Double
	
	def sqrt(x: Double) = sqrtIter(1.0, x)    //> sqrt: (x: Double)Double
	
	sqrt(1e60)                                //> res0: Double = 1.0000788456669446E30
}