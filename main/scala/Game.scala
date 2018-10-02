/**
  * Created by alexandre on 02/10/2018.
  */
class Game {

  def placeableShip(lengthShip:Int,sense:Char,startCell:Cell,heightGrid:Int,lengthGrid:Int):Boolean={
    if(startCell.y <1 ||startCell.y>heightGrid || startCell.x <1 ||startCell.x>lengthGrid) false
    else if(lengthShip==1)true
    else if (sense=='v')  placeableShip(lengthShip-1,sense,Cell(startCell.x,startCell.y+1),heightGrid,lengthGrid)
    else  placeableShip(lengthShip-1,sense,Cell(startCell.x+1,startCell.y),heightGrid,lengthGrid)
  }

  def lapShips(ship: Ship, ships: List[Ship]): Boolean = {
    if (ships.isEmpty) false
    else if (ship.lapShip(ship.cells,ships.head.cells)) true
    else lapShips(ship,ships.tail)
  }
}
