package week_two

object curry_general {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(216); 
  def general(fa: (Int, Int) => Int, acc0: Int)(fw: Int => Int) (start: Int, end: Int): Int={
		if (start>end) acc0 else fa(fw(start), general(fa, acc0)(fw)(start+1, end))
  };System.out.println("""general: (fa: (Int, Int) => Int, acc0: Int)(fw: Int => Int)(start: Int, end: Int)Int""");$skip(66); 
  def factorial(n: Int) = general((x, y) => x*y, 1)(x => x)(1, n);System.out.println("""factorial: (n: Int)Int""");$skip(15); val res$0 = 
  factorial(0);System.out.println("""res0: Int = """ + $show(res$0))}
}
