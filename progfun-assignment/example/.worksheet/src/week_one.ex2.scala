package week_one

object ex2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(327); 
  def balance(s: List[Char]): Boolean={
  	def loop(rest: List[Char], acc: Int):Int={
  		if(rest.isEmpty) acc
  		else if(rest.head=='(') loop(rest.tail, acc+1)
  		else if(rest.head==')') loop(rest.tail, acc-1)
  		else loop(rest.tail, acc)
  		}
  	if(loop(s.toList, 0)==0) true else false
  };System.out.println("""balance: (s: List[Char])Boolean""");$skip(25); val res$0 = 
  balance("())(".toList);System.out.println("""res0: Boolean = """ + $show(res$0))}
}
