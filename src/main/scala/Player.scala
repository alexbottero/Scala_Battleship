/**
  * Created by alexandre on 01/10/2018.
  */
abstract class Player( name:String,  grid: Grid, shots:List[(Int,Int,String)]=List()) {
  val senseValue=Array('h','v')
  val alphabetValue= Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L')

  /**
    *
    * @param ship ship to add to the player
    * @return a new player with the new ship added
    */
  def placeShip(ship: Ship):Player

  /**
    *
    * @return shoot's coordinates
    */
  def play():(Int,Int)


  /**
    *
    * @return true if all the ship of the player are sunk
    */
  def loose():Boolean={
    if (this.grid.ships.forall(s=>s.isSunk()))true else false
  }

  /**
    * Helper method for check input for a shot or to place a boat
    * @param x x value on the grid
    * @param y uy value on the grid
    * @param rows rows of the grid
    * @param columns columns of the grid
    * @return true if the input are corrected
    */
  def checkInput(x:Int, y:Int,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1) false
    else true
  }

  /** Methods to use copy with inheritance
    * Solution provide by Clement Roig to keep using inheritance with case class
    * @param grid grid updated
    * @return a new player with a new grid
    */
  def copyForGrid(grid: Grid): Player

  /**
    *
    * @param shots List of shots updated
    * @return a new player with shots updated
    */
  def copyForShots(shots: List[(Int,Int,String)]): Player

  def getName():String=name
  def getGrid():Grid=grid
  def getShots():List[(Int,Int,String)]=shots

}
