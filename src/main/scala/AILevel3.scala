import scala.annotation.tailrec
import scala.util.Random

/**
  * Created by alexandre on 05/10/2018.
  */
case class AILevel3(name:String="AI Level 3",grid: Grid,shots:List[(Int,Int,String)]=List(),random: Random) extends Player(name,grid,shots) {

  /**
    * Place the ship randomly
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
    * Don't shoot 2 times a the same position and if it hit a cell try to hit all  the cell around
    * @return shoot's coordinates
    */
  override def play(): (Int, Int) = {
    def playRec(shotsHit: List[(Int, Int)]): (Int, Int) = {
      if (shotsHit.isEmpty) {

        val x = random.nextInt(grid.columns)
        val y = random.nextInt(grid.rows)
        if(shots.map(x=>(x._1,x._2)).contains((x,y))) {
          playRec(shotsHit)
        } else (x,y)
      }
      else {
        if (shotsPossible(shotsHit.head._1, shotsHit.head._2).isEmpty) {
          playRec(shotsHit.tail)
        }
        else {
          (shotsPossible(shotsHit.head._1, shotsHit.head._2).head._1, shotsPossible(shotsHit.head._1, shotsHit.head._2).head._2)
        }
      }
    }
    playRec(findShootsTouch(this.shots))
  }

    def findShootsTouch(list: List[(Int,Int,String)]=List()):List[(Int,Int)]={
      this.shots.filter(p=>p._3=="Hit").map(x=>(x._1,x._2))
    }
  def shotsPossible(x:Int,y:Int):List[(Int,Int)]={
    val shotsArround=List((x-1,y),(x+1,y),(x,y-1),(x,y+1))

    val shotMap=this.shots.map(x=>(x._1,x._2))

    val shotsArroundNotTouch=shotsArround.filterNot(p=>shotMap.contains((p._1,p._2)))

    shotsArroundNotTouch.filter(p=>p._1 >=0 && p._1<=grid.rows-1&& p._2 >=0 && p._2<=grid.rows-1)
    }

  override def copyForGrid(grid: Grid): Player = this.copy(grid=grid)

  override def copyForShots(shots: List[(Int, Int, String)]): Player = this.copy(shots=shots)
}
