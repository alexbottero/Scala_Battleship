/**
  * Created by alexandre on 04/10/2018.
  */
case class HumanPlayer(name:String,grid: Grid,shots:List[(Int,Int,String)]=List()) extends Player(name,grid,shots){

  /**
    * Ask the input to the player and try to place the boat
    * @param ship ship to add to the player
    * @return a new player with the new ship added
    */
  override def placeShip(ship: Ship): Player = {
    val x=enterX()
    val y=enterY()
    val sense=enterSense()

      val newGrid=this.grid.addShipOnGrid(x,y,sense,ship)
      if(!newGrid.isDefined){
        print("bad position for a boat")
        placeShip(ship)
      }else{
        this.copy(grid=newGrid.get)
      }

  }

  /**
    * ask the input to the player and shoot on it if there are correct
    * @return shoot's coordinates
    */
  override def play(): (Int, Int) = {
    val x=enterX()
    val y=enterY()
    println(grid.letterToNumber(x),y)
    if (this.checkInput(grid.letterToNumber(x),y,grid.rows,grid.columns)) (grid.letterToNumber(x),y)
    else play()

  }


  /**
    *
    * @return char value fot the shot
    */
  def enterX():Char={
    println("Submit x coordinate between A and J")

    try{
      val x =scala.io.StdIn.readChar()
      if (alphabetValue.contains(x)) x else enterX()
    }catch {
      case _: Exception => enterX()
    }
  }

  /**
    *
    * @return Int value for the shot
    */
  def enterY():Int={
    println("Submit y coordinate between 0 and 9")
    try{
      scala.io.StdIn.readInt()
    }catch {
      case _: Exception => enterY()
    }
  }

  /**
    *
    * @return v or h to represent the ship sense to place the boat
    */
  def enterSense():Char={
    println("Submit sense h for vertical and v for horizontal")
    try{
      val sense =scala.io.StdIn.readChar()
      if(sense!='v'&& sense!='h')enterSense()else sense

    }catch {
      case _: Exception => enterSense()
    }
  }

  override def copyForGrid(grid: Grid): Player = this.copy(grid=grid)

  override def copyForShots(shots: List[(Int, Int, String)]): Player = this.copy(shots=shots)
}
