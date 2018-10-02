import sun.security.util.Length

/**
  * Created by alexandre on 01/10/2018.
  */
case class Ship(cells:List[Cell],touchCells:List[Cell],lengthShip: Int,sense:Char) {

  def isSink(cells:List[Cell],touchCells:List[Cell]):Boolean= cells.length==touchCells.length

  def isTouch(cell: Cell,cells:List[Cell]):Boolean={
    if (cells.isEmpty) false
    else if (cell == cells.head) true
    else isTouch(cell,cells.tail)
  }

  def addCellTouche(cell: Cell,cells:List[Cell]):List[Cell]= cell :: cells

  def lapShip(ship1: List[Cell],ship2: List[Cell]): Boolean ={
    if (ship1.isEmpty) false
    else if (ship2.contains(ship1.head)) true
    else lapShip(ship1.tail,ship2)

  }



}
