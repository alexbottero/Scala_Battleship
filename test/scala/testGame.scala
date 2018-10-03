/**
  * Created by alexandre on 03/10/2018.
  */
object testGame {

  def testCheckCoord(lengthShip:Int,sense:Char,startCell:Cell,heightGrid:Int,lengthGrid:Int,game: Game):Unit={
    println(game.checkCoord(lengthShip,sense,startCell,heightGrid,lengthGrid))
  }

  val cell1=Cell(1,1,'O')
  val cell2=Cell(-1,3,'O')
  val cell3=Cell(3,24,'O')
  val cell4=Cell(9,3,'O')
  val ce
}
