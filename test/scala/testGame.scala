/**
  * Created by alexandre on 03/10/2018.
  */
object testGame extends App{

  def testCheckCoord(lengthShip:Int,sense:Char,startCell:Cell,heightGrid:Int,lengthGrid:Int,game: Game):Unit={
    println(game.checkCoord(lengthShip,sense,startCell,heightGrid,lengthGrid))
  }
  val game=Game()
  val cell1=Cell(1,1,'O')
  val cell2=Cell(-1,3,'O')
  val cell3=Cell(3,24,'O')
  val cell4=Cell(9,3,'O')
  val cell5=Cell(3,9,'O')

  println("Test checkCoord")
  println("Expected result: true ")
  testCheckCoord(2,'h',cell1,10,10,game)
  println("Expected result: true ")
  testCheckCoord(3,'v',cell1,10,10,game)

  println("Expected result: false ")
  testCheckCoord(2,'h',cell2,10,10,game)
  println("Expected result: false ")
  testCheckCoord(2,'h',cell3,10,10,game)
  println("Expected result: false ")
  testCheckCoord(3,'h',cell4,10,10,game)
  println("Expected result: false ")
  testCheckCoord(3,'v',cell5,10,10,game)
}
