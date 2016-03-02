package week_five

object flatten {
  def flatten[T](l: List[T]):List[Any]=
    l match {
      case Nil => Nil
      case y::ys => y match {
        case z::zs => flatten(z::zs) ::: flatten(ys)
        case Nil => flatten(ys)
        case z => y :: flatten(ys)
      }
  }
  
  def main(args: Array[String])={
    val l = List(List(1,2), List(3), 4, 5, List(), 6)
    val t = List(List(0, 3), 1)
    println(flatten(l))
  }
}