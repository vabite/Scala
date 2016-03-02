package week_three

object btree {

  abstract class IntSet{
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
  }

  class NotEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet{

    def incl(x: Int): IntSet=
      if(elem<x) new NotEmpty(elem, left incl x, right)
      else if(elem>x) new NotEmpty(elem, left, right incl x)
      else this

    def contains(x: Int): Boolean=
      if(elem<x) left contains x
      else if(elem>x) right contains x
      else true

    def union(other: IntSet): IntSet=
      other union left union right incl elem

    override def toString = "{"+left.toString()+elem+right.toString()+"}"

  }

  class Empty() extends IntSet{

    def incl(x: Int): IntSet= new NotEmpty(x, new Empty(), new Empty())

    def contains(x: Int): Boolean=false

    def union(other: IntSet): IntSet= other

    override def toString = "."
  }

  def main(args: Array[String]): Unit={
    val t1 = new NotEmpty(5, new Empty(), new Empty()) incl 4 incl 9
    val t2 = new NotEmpty(6, new Empty(), new Empty()) incl 5
    val t3 = t1 union t2
    println(t3.toString())
  }

}