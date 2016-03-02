package funsets
import FunSets._

object test {
    val s12: Set =  x=> x==1||x==2                //> s12  : funsets.FunSets.Set = <function1>
    val s1 = singletonSet(1)                      //> s1  : funsets.FunSets.Set = <function1>
    val s=diff(s12, s1)                           //> s  : funsets.FunSets.Set = <function1>
    printSet(s)                                   //> {2}
}