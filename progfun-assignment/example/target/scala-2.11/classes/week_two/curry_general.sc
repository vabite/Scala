package week_two

object curry_general {
  def general(fa: (Int, Int) => Int, acc0: Int)(fw: Int => Int) (start: Int, end: Int): Int={
		if (start>end) acc0 else fa(fw(start), general(fa, acc0)(fw)(start+1, end))
  }                                               //> general: (fa: (Int, Int) => Int, acc0: Int)(fw: Int => Int)(start: Int, end:
                                                  //|  Int)Int
  def factorial(n: Int) = general((x, y) => x*y, 1)(x => x)(1, n)
                                                  //> factorial: (n: Int)Int
  factorial(0)                                    //> res0: Int = 1
}