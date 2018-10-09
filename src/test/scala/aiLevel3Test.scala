import org.scalatest.FunSuite
import scala.util.Random
/**
  * Created by alexandre on 04/10/2018.
  */
class aiLevel3Test extends FunSuite{
	val grid= Grid(10,10,Array.ofDim[String](10,10).map(x=>x.map(x=>" ")),List())
	val player=AILevel3(grid=grid,shots=List((1,1,"Hit")),random = new Random())
	val s1= Ship(1,2,2)

	test("Test play() AI Level3: Should return (x,y) whith x,y  between 0 and 9, not already shot and near of a hit shot if theireis one" ){
		val coord=player.play()
		assert(coord._1>=0 && coord._1<10 && coord._1>=0 && coord._1<10)
		assert(!(player.shots.map(x=>(x._1,x._2)).contains((coord._1,coord._2))))
		assert(coord==(0,1)||coord==(2,1)||coord==(0,0)||coord==(0,2))
	}
}