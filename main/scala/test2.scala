/**
  * Created by alexandre on 01/10/2018.
  */
import scala.util.matching.Regex

object test2 extends App{

  val l= List((1,2,3),(4,3,2))
  val v=l.map(x=>(x._1,x._2)).contains((2,1))
  print(v)
}
