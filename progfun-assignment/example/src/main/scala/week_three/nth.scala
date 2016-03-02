package week_three
import common._

object nth {

  def nth[T](n: Int, l: List[T]): T={
    if(l.isEmpty) throw new IndexOutOfBoundsException
    else if(n==0) l.head
    else nth(n-1, l.tail)
  }

  def main(args :Array[String]): Unit={
    var l=List[Int](0,1,2,3,4,5)
    println(nth(1, l)+1)
  }
  
}