import org.scalatest.FunSuite
import scala.util.Random
/**
  * Created by alexandre on 04/10/2018.
  */
class aiLevel1Test extends FunSuite{
	val grid= Grid(10,10,Array.ofDim[String](10,10).map(x=>x.map(x=>" ")),List())
	val player=AILevel1(grid=grid,random = new Random())
	val s1= Ship(1,2,2)
	test("Test play() AI Level1: Should return (x,y) whith x,y  between 0 and 9"){
		val coord=player.play()
		assert(coord._1>=0 && coord._1<10 && coord._1>=0 && coord._1<10)
	}

	test("Place Ship AI (the same methods for the 3 AI):Should return the player with a new boat on the list"){
		val newPlayer=player.placeShip(s1)
		assert(newPlayer.getGrid().ships.length==player.getGrid().ships.length+1)
	}
}