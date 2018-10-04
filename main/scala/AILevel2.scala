import scala.util.Random

/**
  * Created by alexandre on 04/10/2018.
  */
case class AILevel2(name:String="AI Level 2",grid: Grid,shots:List[(Int,Int,String)]=List(),random: Random) extends Player(name,grid,shots) {

  val senseValue=Array('h','v')
  val alphabetValue= Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

  override def placeShip(ship: Ship): Player = {
    val x = alphabetValue(random.nextInt(grid.columns))
    val y = random.nextInt(grid.rows)
    val sense=senseValue(random.nextInt(2))
    try{
      val newGrid=this.grid.addShipOnGrid(x,y,sense,ship)
      this.copy(grid=newGrid)
    }catch{
      case _:Exception=>{
        print("bad position for the ship")
        placeShip(ship)
      }
    }
  }

  override def play(): (Int, Int) = {
    (3,4)
  }
}
