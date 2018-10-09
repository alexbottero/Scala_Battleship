import org.scalatest.FunSuite

/**
  * Created by alexandre on 04/10/2018.
  */
class gridTest extends FunSuite{
	val grid =new Grid(10,10,Array.ofDim[String](10,10).map(x=>x.map(x=>" ")),List())
	val s1= Ship(1,2,2)
	test("Test addShip: Should return the new grid with a ship on it"){
		val newGrid=grid.addShipOnGrid('A',0,'v',s1)
		assert(newGrid.ships.length==grid.ships.length+1)

	}
	test("Test shootGrid: hit,miss already hit"){
		val gridWhithship=grid.addShipOnGrid('A',0,'v',s1)

		val newGrid = gridWhithship.updateGrid(gridWhithship.grid, Array((0,0)), "H")

        val hitShip = gridWhithship.ships.filter(_.id.toString == gridWhithship.grid(0)(0)).head.touch()
        val newShips = gridWhithship.ships.filterNot(_.id.toString == gridWhithship.grid(0)(0)) :+ hitShip

		assert((gridWhithship.shootGrid(0,0)._1=="Hit") && (gridWhithship.shootGrid(0,0)._2==Some(hitShip)))

		assert((gridWhithship.shootGrid(3,4)._1=="Miss Shot") && (gridWhithship.shootGrid(3,4)._2==None))
		val grid2=gridWhithship.shootGrid(0,0)._3
		assert((grid2.shootGrid(0,0)._1=="Already hit") && (grid2.shootGrid(0,0)._2==None))
	}
}
