package week_one

object ex3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(375); 
	def countChange(money: Int, coins: List[Int]): Int={
		def loop(amount: Int, values: List[Int], count: Int): Int={
		  if(amount<0 || values.isEmpty) count
		  else if(amount==0) count+1
		  else loop(amount-values.head, values, count)+loop(amount, values.tail, count)
		  }
		if(money<0 || coins.isEmpty) 0
    else loop(money, coins, 0)
   };System.out.println("""countChange: (money: Int, coins: List[Int])Int""");$skip(34); val res$0 = 
   countChange(15, List(6, 1, 7));System.out.println("""res0: Int = """ + $show(res$0))}
 }
