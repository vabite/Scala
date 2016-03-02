package week_two

object tail_product {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(166); 
  def product(fw: Int => Int)(start: Int, end: Int): Int={
		if (start>end) 1 else fw(start)*product(x => x)(start+1, end)
  };System.out.println("""product: (fw: Int => Int)(start: Int, end: Int)Int""");$skip(48); 
  def factorial(n: Int) = product(x => x)(1, n);System.out.println("""factorial: (n: Int)Int""");$skip(15); val res$0 = 
  factorial(3);System.out.println("""res0: Int = """ + $show(res$0))}
}
