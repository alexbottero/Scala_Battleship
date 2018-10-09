import scala.util.Random

/**
  * Created by alexandre on 04/10/2018.
  */
case class AILevel2(name:String="AI Level 2",grid: Grid,shots:List[(Int,Int,String)]=List(),random: Random) extends Player(name,grid,shots) {

  /**
    * put she ship randomly on the grid
    * @param ship ship to add to the player
    * @return a new player with the new ship added
    */
  override def placeShip(ship: Ship): Player = {
    val x = alphabetValue(random.nextInt(grid.columns))
    val y = random.nextInt(grid.rows)
    val sense=senseValue(random.nextInt(2))
    val newGrid=this.grid.addShipOnGrid(x,y,sense,ship)
      if(!newGrid.isDefined){
        placeShip(ship)
      }else{
        this.copy(grid=newGrid.get)
      }
  }

  /**
    * Shoot randomly but not 2 times a the same position
    * @return shoot's coordinates
    */
  override def play(): (Int, Int) = {
    val x = random.nextInt(grid.columns)
    val y = random.nextInt(grid.rows)
    if(shots.map(x=>(x._1,x._2)).contains((x,y))) {
      play()
    } else (x,y)


  }

  override def copyForGrid(grid: Grid): Player = this.copy(grid=grid)

  override def copyForShots(shots: List[(Int, Int, String)]): Player = this.copy(shots=shots)
}
