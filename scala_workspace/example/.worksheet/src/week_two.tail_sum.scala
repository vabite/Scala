package week_two

object tail_sum {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(202); 
    def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a>b) acc
      else loop(a+1, acc+f(a))
    }
    loop(a, 0)
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(18); val res$0 = 
  sum(x=>x, 5, 4);System.out.println("""res0: Int = """ + $show(res$0))}
}
