package patmat

import Huffman.decodedSecret

object prove {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(120); 
	def isPrime(n: Int): Boolean= 2 to n foreach (d => n%d!=0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(130); 
	def primePairs(n: Int): Seq[(Int, Int)]=1 to n map (i => 1 to i map (j => (j, i))) flatMap(x=>x) filter(x => isPrime(x._1+x._2));System.out.println("""primePairs: (n: Int)Seq[(Int, Int)]""");$skip(25); 
  println(primePairs(2))}
}
