import sun.security.util.Length

/**
  * Created by alexandre on 01/10/2018.
  */
case class Ship(id:Int,numberOfCell: Int,cellNotTouch:Int){

  def this(numberOfCell:Int){
    this(numberOfCell,numberOfCell)
  }

  def isSunk():Boolean= this.cellNotTouch==0

  def isTouch():Ship=this.copy(cellNotTouch=if(cellNotTouch<0)0 else this.cellNotTouch-1)
}


//{
/*
  def isSink(ship: Ship):Boolean = ship.cells.isEmpty

  def isTouch(cell: Cell,ship: Ship):Boolean={
    if (ship.cells.isEmpty) false
    else if (cell == ship.cells.head) true
    else isTouch(cell,ship.copy(cells=ship.cells.tail))
  }

  def removeCell(cell: Cell,ship: Ship):Ship=ship.copy(cells=ship.cells.filter(_!=cell),lengthShip=ship.cells.filter(_!=cell).length)



  def lapShip(ship1: Ship,ship2: Ship): Boolean ={
    if (ship1.cells.isEmpty) false
    else if (ship2.cells.contains(ship1.cells.head)) true
    else lapShip(ship1.copy(cells=ship1.cells.tail,lengthShip=ship1.lengthShip-1),ship2)

  }


}*/
