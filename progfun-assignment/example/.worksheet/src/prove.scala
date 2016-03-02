
object prove {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(217); 
	def checkCol(col: Int, queens: List[Int]): Boolean={
		val (otherQueenCol, deltaRows)=(queens.head, queens.length)
		if((queens contains col) || (col==queens.head-queens.length)) false
		else true
		};System.out.println("""checkCol: (col: Int, queens: List[Int])Boolean""")}
}
