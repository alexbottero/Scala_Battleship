import scala.util.Random

/**
  * Created by alexandre on 04/10/2018.
  */
case class AILevel2(name:String="AI Level 2",grid: Grid,shots:List[(Int,Int,String)]=List(),random: Random) extends Player(name,grid,shots) {


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
