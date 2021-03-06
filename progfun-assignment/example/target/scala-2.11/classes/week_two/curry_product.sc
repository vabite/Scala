package week_two

object tail_product {
  def product(fw: Int => Int)(start: Int, end: Int): Int={
		if (start>end) 1 else fw(start)*product(x => x)(start+1, end)
  }                                               //> product: (fw: Int => Int)(start: Int, end: Int)Int
  def factorial(n: Int) = product(x => x)(1, n)   //> factorial: (n: Int)Int
  factorial(3)                                    //> res0: Int = 6
}