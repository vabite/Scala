package week_two

object tail_general {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(490);  //forse cìè un modo per non passare acc0 e di ricavarlo internamente come elemento neutro della funzione facc
  def acc(fa:(Int, Int) => Int, cum0: Int):(Int => Int)=>(Int, Int)=>Int ={
    def weighted_acc(fw: Int => Int):((Int, Int)=>Int) ={
    	def run(start: Int, end: Int) ={
  			def loop(next: Int, cum: Int): Int=
  				if(next>end) cum
  				else loop(next+1, fa(cum, fw(next)))
  			loop(start, cum0)
  		}
  		run
  	}
  	weighted_acc
  };System.out.println("""acc: (fa: (Int, Int) => Int, cum0: Int)(Int => Int) => ((Int, Int) => Int)""");$skip(36); 
	def product= acc((x, y) => x*y, 1);System.out.println("""product: => (Int => Int) => ((Int, Int) => Int)""");$skip(33); 
  def sum= acc((x, y) => x+y, 0);System.out.println("""sum: => (Int => Int) => ((Int, Int) => Int)""");$skip(32); 
	def factorial= product(x => x);System.out.println("""factorial: => (Int, Int) => Int""");$skip(17); val res$0 = 
	factorial(0, 5);System.out.println("""res0: Int = """ + $show(res$0))}
}
