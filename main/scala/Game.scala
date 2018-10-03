/**
  * Created by alexandre on 02/10/2018.
  */
class Game extends App{




  def checkCoord(lengthShip:Int,sense:Char,startCell:Cell,heightGrid:Int,lengthGrid:Int):Boolean={
    if(startCell.y <1 || startCell.y>heightGrid || startCell.x <1 ||startCell.x>lengthGrid) false
    else if(lengthShip==1)true
    else if (sense=='v')   checkCoord(lengthShip-1,sense,startCell.copy(y=startCell.y+1),heightGrid,lengthGrid)
    else   checkCoord(lengthShip-1,sense,Cell(startCell.x+1,startCell.y),heightGrid,lengthGrid)
  }



  def numberToLetter(char: Char): Int = {
    char match {
      case 'A'| 'a'=>1
      case 'B'| 'b'=>2
      case 'C'| 'c'=>3
      case 'D'| 'd'=>4
      case 'E'| 'e'=>5
      case 'F'| 'f'=>6
      case 'I'| 'i'=>7
      case 'J'| 'j'=>8
      case 'K'| 'k'=>9
      case 'L'| 'l'=>10
      case _=> throw new Exception("bad value")

    }
  }
}
