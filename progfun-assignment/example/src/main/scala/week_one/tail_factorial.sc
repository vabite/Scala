package week_one

object tail_factorial {

	def factorial(n: Int):Int = {
	
	  def factorialLoop(rest: Int, cum: Int): Int={
	  	if(rest==0) cum
	  	else factorialLoop(rest-1, rest*cum)
	  }
	  	
	  factorialLoop(n, 1)
	 }                                        //> factorial: (n: Int)Int
	 
	 factorial(3)                             //> res0: Int = 6
 }