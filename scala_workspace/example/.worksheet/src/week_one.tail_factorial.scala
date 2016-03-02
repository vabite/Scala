package week_one

object tail_factorial {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(222); 

	def factorial(n: Int):Int = {
	
	  def factorialLoop(rest: Int, cum: Int): Int={
	  	if(rest==0) cum
	  	else factorialLoop(rest-1, rest*cum)
	  }
	  	
	  factorialLoop(n, 1)
	 };System.out.println("""factorial: (n: Int)Int""");$skip(18); val res$0 = 
	 
	 factorial(3);System.out.println("""res0: Int = """ + $show(res$0))}
 }
