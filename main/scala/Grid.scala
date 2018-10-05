/**
  * Created by alexandre on 04/10/2018.
  */


case class Grid (rows:Int, columns:Int,grid:Array[Array[String]],ships:List[Ship]){


  def this(rows:Int,col:Int){
    this(rows,col,Array.ofDim[String](rows,col).map(x=>x.map(x=>" ")),List())
  }

  /**
    *
    * @param x coord x axis
    * @param y coord y axis
    * @param sense h for horizontal or v vertical
    * @param ship to add to the grid
    * @return New grid whith the new ship
    *
    */
  def addShipOnGrid(x:Char,y:Int,sense:Char,ship: Ship): Grid ={
      val xInt=letterToNumber(x)
      if (!checkInput(xInt,y,sense,this.rows,this.columns)) throw new Exception("Invalid inputs (positions or sense value)")
      val futureCellsShip=this.futureCellsOfShip(xInt,y,sense,ship.numberOfCell,Array())
      futureCellsShip.foreach(t=>{
        if(t._1 < 0||t._2 < 0||t._2 >= this.rows||t._1 >= this.columns) throw new Exception("Ship is outside the grid")

        if(this.grid(xInt)(y)!=" ") throw new Exception("Cell already used")
      })
      val newGrid=this.updateGrid(this.grid,futureCellsShip,ship.id.toString)
      this.copy(grid=newGrid,ships = this.ships:+ship)

  }

  def updateGrid(grid:Array[Array[String]], cellsToUpdate:Array[(Int,Int)], newCell: String): Array[Array[String]] ={

    if (cellsToUpdate.length==0) grid
    else {
      val coordCellUpdate=cellsToUpdate.head
      println(coordCellUpdate+"  ")
      val newGrid=Array.ofDim[String](this.rows,this.columns)
      grid.zipWithIndex.foreach{
        case(row,i) => {
          if(i == coordCellUpdate._2) {
            row.zipWithIndex.foreach{
              case(value, j) => {
                if(j == coordCellUpdate._1) {
                  newGrid(i)(j) = newCell
                  }
                else {
                  newGrid(i)(j) = value
                }
              }
            }
          } else {
            newGrid(i) = row
          }
        }
      }
      updateGrid(newGrid,cellsToUpdate.tail,newCell)

    }
  }

  def futureCellsOfShip(x:Int,y:Int,sense:Char,shipSize:Int,cellsShip:Array[(Int,Int)]): Array[(Int,Int)] ={
    if (shipSize==1) cellsShip:+(x,y)
    else{
      val newCellsShip=cellsShip:+(x,y)
      if (sense=='h') futureCellsOfShip(x+1,y,sense,shipSize-1,newCellsShip)
      else futureCellsOfShip(x,y+1,sense,shipSize-1,newCellsShip)
    }

  }

  def shootGrid(x: Int, y: Int):  (String,Option[Ship],Grid) = {
    if(this.grid(x)(y)!=" ") {

      if(this.grid(x)(y)=="H"||this.grid(x)(y)=="X") {

        ("Already hit",None, this.copy())
      } else {
        val newGrid = this.updateGrid(this.grid, Array((y,x)),  "H")
        val hitShip = this.ships.filter(_.id.toString == this.grid(x)(y)).head.touch()
        val newShips = this.ships.filterNot(_.id.toString == this.grid(x)(y)) :+ hitShip

        (if(hitShip.isSunk()) "Sunk" else "Hit", Some(hitShip),this.copy(ships=newShips,grid=newGrid))
      }

    } else {

      val newGrid = this.updateGrid(this.grid, Array((x,y)), "X")
      ("Miss Shot",None, this.copy(grid=newGrid))
    }
  }

  def letterToNumber(char: Char): Int = {
    char match {
      case 'A' | 'a' => 0
      case 'B' | 'b' => 1
      case 'C' | 'c' => 2
      case 'D' | 'd' => 3
      case 'E' | 'e' => 4
      case 'F' | 'f' => 5
      case 'I' | 'i' => 6
      case 'J' | 'j' => 7
      case 'K' | 'k' => 8
      case 'L' | 'l' => 9
      case _ => throw new Exception("bad value")

    }
  }

  def displayGrid():String={
    var gridToDisplay="    A   B   C   D   E   F   I   J   K   L  \n" +
      "   ________________________________________\n"
    this.grid.zipWithIndex.foreach {
      case(x,i) => {
        if(i != 0) gridToDisplay = gridToDisplay+("|\n"+i+" ")
        else gridToDisplay = gridToDisplay+("0 ")
        x.foreach { x => {
          gridToDisplay = gridToDisplay+("|")+"_"+x+"_"
        }}
      }
    }
    gridToDisplay = gridToDisplay+("|\n\n")
    gridToDisplay
  }


  def checkInput(x:Int, y:Int, sense:Char,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1 || (sense!='h'&& sense!='v')) false
    else true
  }


}
