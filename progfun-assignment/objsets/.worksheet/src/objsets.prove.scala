package objsets

import Huffman.scala

object prove {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(301); 


      
    def merge(lt: List[Int], ls: List[Int]):List[Int]=
     (lt, ls) match {
       case (xs, List())=> xs
       case (List(), ys)=> ys
       case (x::xs, y::ys)=> {
       	if(x > y) x::merge(xs,ls)
       	else y::merge(lt,ys)}
     };System.out.println("""merge: (lt: List[Int], ls: List[Int])List[Int]""");$skip(198); 
    
    def sort(l: List[Int]):List[Int]={
    	val n=l.length/2
      if(n==0) l
      else {
      	val halves = (l take n, l drop n)
      	merge(sort(halves._1), sort(halves._2))
      }
    };System.out.println("""sort: (l: List[Int])List[Int]""");$skip(36); 
    
    val l=List(0,1,2,32,4,5,6);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(29); 
    println("ciao", sort(l))}
 }
