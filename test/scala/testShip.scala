/**
  * Created by alexandre on 02/10/2018.
  */
object testShip extends App{



  def testIsSink(ship: Ship)=println(ship.isSink(ship))

  def testIsTouch(cell: Cell,ship: Ship):Unit=println(ship.isTouch(cell,ship))

  def testRemoveCell(cell: Cell,ship: Ship):Unit={
    val s2=ship.removeCell(cell,ship)
    println(cell+" "+s2 +" "+ ship)
    println(s2.lengthShip+" "+(ship.lengthShip-1))

    println(s2.lengthShip==ship.lengthShip-1)

  }

  def testLapShip(ship1: Ship,ship2: Ship):Unit=println(ship1.lapShip(ship1,ship2))


  val shipEmpty=Ship(List(),1,'h')
  val ship1=Ship(List(Cell(1,1,'O'),Cell(1,2,'O')),2,'v')
  val ship2=Ship(List(Cell(1,1,'O'),Cell(2,1,'O')),2,'h')
  val ship3=Ship(List(Cell(2,2,'O'),Cell(2,1,'O')),2,'h')
  val ship4=Ship(List(Cell(9,8,'O'),Cell(4,4,'O')),2,'h')

  val c1=Cell(1,1,'O')

  val c2=Cell(1,2,'O')
  val c3=Cell(1,8,'O')

  println("Test IsSink")
  println("Expected result: true")
  testIsSink(shipEmpty)
  println("Expected  result: false")
  testIsSink(ship1)

  println("Test IsTouch")
  println("Expected  result: true")
  testIsTouch(c1,ship1)
  println("Expected  result: false")
  testIsTouch(c3,ship1)

  println("Test RemoveCell")
  println("Expected  result: true")
  testRemoveCell(c1,ship1)
  println("Expected  result: true")
  testRemoveCell(c2,ship1)
  println("Expected  result: false")
  testRemoveCell(c3,ship1)


  println("Test LapShip")
  println("Expected  result: true")
  testLapShip(ship1,ship2)
  println("Expected result: true")
  testLapShip(ship2,ship3)
  println("Expected result: false")
  testLapShip(ship3,ship4)


}
