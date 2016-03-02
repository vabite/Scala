package week_two

object tail_sum {
    def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a>b) acc
      else loop(a+1, acc+f(a))
    }
    loop(a, 0)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
  sum(x=>x, 5, 4)                                 //> res0: Int = 0
}