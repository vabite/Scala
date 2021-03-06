package week_one

object ex3 {
	def countChange(money: Int, coins: List[Int]): Int={
		def loop(amount: Int, values: List[Int], count: Int): Int={
		  if(amount<0 || values.isEmpty) count
		  else if(amount==0) count+1
		  else loop(amount-values.head, values, count)+loop(amount, values.tail, count)
		  }
		if(money<0 || coins.isEmpty) 0
    else loop(money, coins, 0)
   }                                              //> countChange: (money: Int, coins: List[Int])Int
   countChange(15, List(6, 1, 7))                 //> res0: Int = 6
 }