package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = if(c==0 || c==r) 1 else pascal(c-1, r)+pascal(c, r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean ={
    def loop(rest: List[Char], acc: Int):Int={
  		if(rest.isEmpty || acc<0) acc
  		else if(rest.head=='(') loop(rest.tail, acc+1)
  		else if(rest.head==')') loop(rest.tail, acc-1)
  		else loop(rest.tail, acc)
  		}
  	if(loop(chars, 0)==0) true else false
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
		def loop(amount: Int, values: List[Int], count: Int): Int={
		  if(amount<0 || values.isEmpty) count
		  else if(amount==0) count+1
		  else loop(amount-values.head, values, count)+loop(amount, values.tail, count)
		  }
	  if(money<0 || coins.isEmpty) 0
    else loop(money, coins, 0)
    }                                              
  }
