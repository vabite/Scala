package week_one

object ex2 {
  def balance(s: List[Char]): Boolean={
  	def loop(rest: List[Char], acc: Int):Int={
  		if(rest.isEmpty) acc
  		else if(rest.head=='(') loop(rest.tail, acc+1)
  		else if(rest.head==')') loop(rest.tail, acc-1)
  		else loop(rest.tail, acc)
  		}
  	if(loop(s.toList, 0)==0) true else false
  }                                               //> balance: (s: List[Char])Boolean
  balance("())(".toList)                          //> res0: Boolean = true
}