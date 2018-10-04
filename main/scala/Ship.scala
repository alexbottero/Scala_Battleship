import sun.security.util.Length

/**
  * Created by alexandre on 01/10/2018.
  */
case class Ship(id:Int,numberOfCell: Int,cellNotTouch:Int){
  /**
    *
    * @return true if the ship is sunk
    */
  def isSunk():Boolean= this.cellNotTouch==0

  /**
    *
    * @return a new ship with a cellNotTouch decremented by 1 cause the boat was touch
    */
  def touch():Ship=this.copy(cellNotTouch=if(cellNotTouch<=0)0 else this.cellNotTouch-1)
}


