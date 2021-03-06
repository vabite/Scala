package week_two

object fixedpointSqrt {
  def fixedPoint(f: Double => Double)(initGuess: Double)={
  	def iteration(guess: Double): Double={
  		val tolerance = 0.001
  		val nextGuess=f(guess)
  		if(nextGuess-guess < tolerance*guess) nextGuess
  		else iteration(nextGuess)
  	}
  	iteration(initGuess)
  }                                               //> fixedPoint: (f: Double => Double)(initGuess: Double)Double
  def avgDamp(f: Double => Double)(x: Double) = (f(x) + x)/2
                                                  //> avgDamp: (f: Double => Double)(x: Double)Double
  def sqrt(x: Double)=fixedPoint(avgDamp(y=>x/y))(1.0)
                                                  //> sqrt: (x: Double)Double
  sqrt(2)                                         //> res0: Double = 1.4166666666666665
}