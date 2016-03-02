package week_four

object Main {
  
  object Lista {
    
    def apply[T](): List[T]=List()
    
    def apply[T](x0:T): List[T]=List(x0)
    
    def apply[T](x0:T, x1:T): List[T]=List(x0, x1)
     
  }
  
  def main(args: Array[String]): Unit = println(Lista(2,3).toString())
}