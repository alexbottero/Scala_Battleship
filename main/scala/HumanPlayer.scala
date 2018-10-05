/**
  * Created by alexandre on 04/10/2018.
  */
case class HumanPlayer(name:String,grid: Grid,shots:List[(Int,Int,String)]=List()) extends Player(name,grid,shots){


  override def placeShip(ship: Ship): Player = {
    val x=enterX()
    val y=enterY()
    val sense=enterSense()

    try{
      val newGrid=this.grid.addShipOnGrid(x,y,sense,ship)
      this.copy(grid=newGrid)
    }catch{
      case _:Exception=>{
        println("bad position for the ship")
        placeShip(ship)
      }
    }

  }

  override def play(): (Int, Int) = {
    val x=enterX()
    val y=enterY()
    if (this.checkInput(grid.letterToNumber(x),y,grid.rows,grid.columns)) (grid.letterToNumber(x),y)
    else play()

  }


  def enterX():Char={
    println("Submit x coordinate between A and J")

    try{
      val x =scala.io.StdIn.readChar()
      if (alphabetValue.contains(x)) x else enterX()
    }catch {
      case _: Exception => enterX()
    }
  }

  def enterY():Int={
    println("Submit y coordinate between 0 and 9")
    try{
      scala.io.StdIn.readInt()
    }catch {
      case _: Exception => enterY()
    }
  }

  def enterSense():Char={
    println("Submit sense h for vertical and v for horizontal")
    try{
      scala.io.StdIn.readChar()
    }catch {
      case _: Exception => enterSense()
    }
  }

  override def copyForGrid(grid: Grid): Player = this.copy(grid=grid)

  override def copyForShots(shots: List[(Int, Int, String)]): Player = this.copy(shots=shots)
}
