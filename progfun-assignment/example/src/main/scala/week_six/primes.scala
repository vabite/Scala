package week_six

object primes {

	def isPrime(n: Int): Boolean= 2 until n forall (d => n%d!=0)
	def primePairs(n: Int): Seq[(Int, Int)]=
	  1 to n flatMap (i => 1 to i map (j => (j, i))) filter({case (x,y)=>isPrime(x+y)})
  def main(args: Array[String])=println(primePairs(2))

}