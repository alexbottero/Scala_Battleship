import sun.security.util.Length

/**
  * Created by alexandre on 01/10/2018.
  */
case class Ship(id:Int,numberOfCell: Int,cellNotTouch:Int){

  def isSunk():Boolean= this.cellNotTouch==0

  def isTouch():Ship=this.copy(cellNotTouch=if(cellNotTouch<0)0 else this.cellNotTouch-1)
}


