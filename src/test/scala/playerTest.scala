import org.scalatest.FunSuite
import scala.util.Random

/**
  * Created by alexandre on 09/10/2018.
  */
class playerTest extends FunSuite{
  val s1= Ship(1,5,0)
  val s2= Ship(1,3,0)
  val s3=Ship(1,3,2)
  val grid= Grid(10,10,Array.ofDim[String](10,10).map(x=>x.map(x=>" ")),List(s1,s2))
  val grid2= Grid(10,10,Array.ofDim[String](10,10).map(x=>x.map(x=>" ")),List(s1,s2,s3))
 test("Test loose function HumanPlayer: Have to return true if all the ship are sunk "){
   assert( HumanPlayer("bob",grid).loose())
 }
  test("Test loose function AI1: Have to return true if all the ship are sunk "){
    assert( AILevel1(grid=grid,random = new Random()).loose())
    assert( AILevel1(grid=grid2,random = new Random()).loose()==false)
  }
  test("Test loose function AI2: Have to return true if all the ship are sunk "){
    assert(AILevel2(grid=grid,random = new Random()).loose())
    assert( AILevel1(grid=grid2,random = new Random()).loose()==false)
  }
  test("Test loose function AI3: Have to return true if all the ship are sunk "){
    assert( AILevel3(grid=grid,random = new Random()).loose())
    assert( AILevel1(grid=grid2,random = new Random()).loose()==false)
  }
  
}
