package week_two

object product {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(192); 
  def prod(fw: Int => Int)(s: Int)(e: Int)={
  	 def loop(next: Int, acc: Int): Int=
  		if(next>e) acc
  		else loop(next+1, acc*fw(next))
  	loop(s, 1)
  };System.out.println("""prod: (fw: Int => Int)(s: Int)(e: Int)Int""")}
}
