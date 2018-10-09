import scala.util.Random

/**
  * Created by alexandre on 04/10/2018.
  */
case class AILevel1(name:String="AI Level 1",grid: Grid,shots:List[(Int,Int,String)]=List(),random: Random) extends Player(name,grid,shots){

  /**
    * place randomly the ship
    * @param ship ship to add to the player
    * @return a new player with the new ship added
    */
  override def placeShip(ship: Ship): Player = {
    val x = alphabetValue(random.nextInt(grid.columns))
    val y = random.nextInt(grid.rows)
    val sense=senseValue(random.nextInt(2))
    try{
      val newGrid=this.grid.addShipOnGrid(x,y,sense,ship)
      this.copy(grid=newGrid)
    }catch{
      case _:Exception=>{
        placeShip(ship)
      }
    }
  }

  /**
    * Shoot randomly (can shoot many times a the same coordinates)
    * @return shoot's coordinates
    */
  override def play(): (Int, Int) = {
    val x = random.nextInt(grid.columns)
    val y = random.nextInt(grid.rows)
    (x,y)
  }
  override def copyForGrid(grid: Grid): Player = this.copy(grid=grid)

  override def copyForShots(shots: List[(Int, Int, String)]): Player = this.copy(shots=shots)
}
