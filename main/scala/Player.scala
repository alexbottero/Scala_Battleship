/**
  * Created by alexandre on 01/10/2018.
  */
abstract class Player( name:String,  grid: Grid, shots:List[(Int,Int,String)]=List()) {

  def placeShip(ship: Ship):Player
  def play():(Int,Int)



  def loose():Boolean={
    if (this.grid.ships.forall(s=>s.isSunk()))true else false
  }
  def checkInput(x:Int, y:Int,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1) false
    else true
  }

  def copyForGrid(grid: Grid): Player
  def copyForShots(shots: List[(Int,Int,String)]): Player

  def getName():String=name
  def getGrid():Grid=grid
  def getShots():List[(Int,Int,String)]=shots

}
