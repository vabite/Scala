package funsets
import FunSets._

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 
    val s12: Set =  x=> x==1||x==2;System.out.println("""s12  : funsets.FunSets.Set = """ + $show(s12 ));$skip(29); 
    val s1 = singletonSet(1);System.out.println("""s1  : funsets.FunSets.Set = """ + $show(s1 ));$skip(24); 
    val s=diff(s12, s1);System.out.println("""s  : funsets.FunSets.Set = """ + $show(s ));$skip(16); 
    printSet(s)}
}
