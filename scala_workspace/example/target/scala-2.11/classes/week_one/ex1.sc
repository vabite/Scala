package week_one

object trial {
    def pascal(c: Int, r: Int): Int= if(c==0 || c==r) 1 else pascal(c-1, r)+pascal(c, r-1)
                                                  //> pascal: (c: Int, r: Int)Int
  	pascal(1 , 3)                             //> res0: Int = 3
}