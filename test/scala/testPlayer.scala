/**
  * Created by alexandre on 02/10/2018.
  */
/*
object testPlayer extends App{
  val player=Player(List(),"alexandre",List())
  val player2=Player(List(Ship(List(Cell(1,1,'O'),Cell(1,2,'O')),2,'v'),Ship(List(Cell(9,8,'O'),Cell(4,4,'O')),2,'h')),"vincent",List())
  val ship3=Ship(List(Cell(1,1,'O'),Cell(1,2,'O')),2,'v')
  val ship2=Ship(List(Cell(8,4,'O')),2,'v')

  def testPlayerLoose(player: Player):Unit=println(player.playerLoose(player))
  def testRemoveShip(ship: Ship,player: Player):Unit={
    val p2=player.removeShip(ship,player)
    println(p2.ships.length==player.ships.length-1)
  }
  def testPlaceShip(ship: Ship,player: Player):Unit=println(player.placeShip(player,ship).ships.length==(player.ships.length+1))
  def testLapShip(ship: Ship,player: Player):Unit=println(player.lapShips(ship,player))

  println("Test PlayerLoose")
  println("Expected  result: true")
  testPlayerLoose(player)
  println("Expected  result: false")
  testPlayerLoose(player2)

  println("Test RemoveShip")
  println("Expected  result: true")
  testRemoveShip(ship3,player2)
  println("Expected  result: false")
  testRemoveShip(ship2,player2)

  println("Test LapShips")
  println("Expected  result: true")
  testLapShip(ship3,player2)
  println("Expected  result: false")
  testLapShip(ship2,player2)

  println("Test placeShip")
  println("Expected  result: true")
  testLapShip(ship3,player2)
  println("Expected  result: false")
  testLapShip(ship2,player2)


}
*/
