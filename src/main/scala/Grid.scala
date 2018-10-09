/**
  * Created by alexandre on 04/10/2018.
  */


case class Grid (rows:Int, columns:Int,grid:Array[Array[String]],ships:List[Ship]){

  /**
    * Init the grid with the default value " "
    * @param rows number of rows of the grid
    * @param col number of columns of the grid
    */
  def this(rows:Int,col:Int){
    this(rows,col,Array.ofDim[String](rows,col).map(x=>x.map(x=>" ")),List())
  }

  /**
    *Method to add a ship on a grid
    * @param x coord x axis
    * @param y coord y axis
    * @param sense h for horizontal or v vertical
    * @param ship to add to the grid
    * @return New grid whith the new ship added
    *
    */
  def addShipOnGrid(x:Char,y:Int,sense:Char,ship: Ship): Option[Grid] ={
      val xInt=letterToNumber(x)

      if (!checkInput(xInt,y,sense,this.rows,this.columns)) None

      val futureCellsShip=this.futureCellsOfShip(xInt,y,sense,ship.numberOfCell,Array())

    futureCellsShip.foreach(t=>{
        if(t._1 < 0||t._2 < 0||t._2 >= this.rows||t._1 >= this.columns) return None
        if(this.grid(t._1)(t._2)!=" "){
          return None
        }
      })
      val newGrid=this.updateGrid(this.grid,futureCellsShip,ship.id.toString)
      Some(this.copy(grid=newGrid,ships = this.ships:+ship))

  }

  /**
    * Method to update the value inside the grid
    * @param grid current grid that will be updated
    * @param cellsToUpdate array of cells that will be updated
    * @param newCell value for the cells updated
    * @return A new grid with the cells updated
    */
  def updateGrid(grid:Array[Array[String]], cellsToUpdate:Array[(Int,Int)], newCell: String): Array[Array[String]] ={

    if (cellsToUpdate.length==0) grid
    else {
      val coordCellUpdate=cellsToUpdate.head
      val newGrid=Array.ofDim[String](this.rows,this.columns)
      grid.zipWithIndex.foreach{
        case(list,row) => {
          if(row == coordCellUpdate._1) {
            list.zipWithIndex.foreach{
              case(value, col) => {
                if(col == coordCellUpdate._2) {
                  newGrid(row)(col) = newCell
                  }
                else {
                  newGrid(row)(col) = value
                }
              }
            }
          } else {
            newGrid(row) = list
          }
        }
      }
      updateGrid(newGrid,cellsToUpdate.tail,newCell)

    }
  }

  /**
    * Compute the future cell of a ship
    * @param x value x of the started cell
    * @param y value y of the started cell
    * @param sense sense of the ship vertical or horizontal
    * @param shipSize size of the ship (2,3,4 or 5)
    * @param cellsShip All the cell of the ship ( Initial value Array())
    * @return All the cell of the ship Array of (y,x)
    */
  def futureCellsOfShip(x:Int,y:Int,sense:Char,shipSize:Int,cellsShip:Array[(Int,Int)]): Array[(Int,Int)] ={
    if (shipSize==1) cellsShip:+(y,x)
    else{
      val newCellsShip=cellsShip:+(y,x)
      if (sense=='h') futureCellsOfShip(x+1,y,sense,shipSize-1,newCellsShip)
      else futureCellsOfShip(x,y+1,sense,shipSize-1,newCellsShip)
    }

  }

  /**
    * Method to shoot on the enemy grid
    * @param x x value of the shot
    * @param y y value of the shot
    * @return the result of the shot, the ship hit if there is one,the new grid updated
    */
  def shootGrid(x: Int, y: Int):  (String,Option[Ship],Grid) = {
    if(this.grid(x)(y)!=" ") {
      if(this.grid(x)(y)=="H"||this.grid(x)(y)=="X") {

        ("Already hit",None, this)
      } else {
        val newGrid = this.updateGrid(this.grid, Array((x,y)), "H")
        val hitShip = this.ships.filter(_.id.toString == this.grid(x)(y)).head.touch()
        val newShips = this.ships.filterNot(_.id.toString == this.grid(x)(y)) :+ hitShip

        (if(hitShip.isSunk()) "Sunk" else "Hit", Some(hitShip),this.copy(ships=newShips,grid=newGrid))
      }

    } else {

      val newGrid = this.updateGrid(this.grid, Array((x,y)), "X")
      ("Miss Shot",None, this.copy(grid=newGrid))
    }
  }
  /**
    *
    * @return String representing the grid of the player and the enemy shot on his grid
    */
  def displayGrid():String={
    var gridToDisplay="    A   B   C   D   E   F   I   J   K   L  \n" +
      "   -------------------------------------------\n"
    this.grid.zipWithIndex.foreach {
      case(x,i) =>
        if(i != 0) gridToDisplay +="|\n"+i+" "
        else gridToDisplay +="0 "
        x.foreach { x => {
          gridToDisplay = gridToDisplay+"|_"+x+"_"
        }
        }
    }
    gridToDisplay +="|\n\n"
    gridToDisplay
  }

  /**
    *
    * @return String representing the grid with our shots on the enemy grid
    */
  def displayGridShot():String={
    var gridToDisplay="    A   B   C   D   E   F   I   J   K   L  \n" +
      "  --------------------------------------------\n"
    this.grid.zipWithIndex.foreach {
      case(x,i) =>
        if(i != 0) gridToDisplay +=("|\n"+i+" ")
        else gridToDisplay += "0 "
        x.foreach { x => {
          gridToDisplay +="|_"
          if (x=="H"||x=="X") gridToDisplay+=x else gridToDisplay+=" "
          gridToDisplay+="_"
        }
        }
    }
    gridToDisplay = gridToDisplay+"|\n\n"
    gridToDisplay
  }


  /**
    * Helper method to transform a char in int
    * @param char value in Char
    * @return value in Int
    */
  def letterToNumber(char: Char): Int = {
    char match {
      case 'A' | 'a' => 0
      case 'B' | 'b' => 1
      case 'C' | 'c' => 2
      case 'D' | 'd' => 3
      case 'E' | 'e' => 4
      case 'F' | 'f' => 5
      case 'G' | 'g' => 6
      case 'H' | 'h' => 7
      case 'I' | 'i' => 8
      case 'J' | 'j' => 9

    }
  }


  /**
    * Helper method for check input
    * @param x value
    * @param y value
    * @param sense sense of the ship
    * @param rows rows of the grid
    * @param columns columns of the grid
    * @return true if all the inputs are correct
    */
  def checkInput(x:Int, y:Int, sense:Char,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1 || (sense!='h'&& sense!='v')) false
    else true
  }


}
