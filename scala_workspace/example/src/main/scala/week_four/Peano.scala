package week_four

object Naturals {
  
  abstract class Nat {
    def isZero: Boolean
    def predecessor: Nat
    def successor: Nat = new Succ(this)
    def + (that: Nat): Nat
    def - (that: Nat): Nat
    def toInt: Int
    def toString: String
  }
  
  object Zero extends Nat {
    def isZero: Boolean=true
    def predecessor: Nothing=throw new Error("Zero.predecessor")
    def + (that: Nat): Nat = that
    def - (that: Nat): Nat = if(that.isZero) that else throw new Error("negative number")
    override def toInt: Int=0
    override def toString: String = "0"
  }
  
  class Succ(n: Nat) extends Nat{
    def isZero: Boolean=false
    def predecessor: Nat = n
    def + (that: Nat): Nat= new Succ(n + that) 
    def - (that: Nat): Nat=if(that.isZero) this else n-that.predecessor
    override def toInt: Int = n.toInt + 1
    override def toString: String = "S(" + n.toString() + ")"
  }
  
  def main(args: Array[String]){
    val zero = Zero
    val one = new Succ(Zero)
    val two = new Succ(one)
    val three = new Succ(two)
    println("0+0=" +(zero + zero).toString)
    println("S(0)+S(0)=" +(one + one).toString)
    println("S(0)+S(S(0))=" +(one + two).toString)
    println("S(S(0)-S(0))=" +(two - one).toString)
    println("S(0)-S(S(0))=" +(one - two).toInt)
  }
  
}
