/**
  * Created by alexandre on 04/10/2018.
  */


case class Grid (rows:Int, columns:Int,grid:Array[Array[Cell]],ships:List[Ship]){


  def this(rows:Int,col:Int){
    this(rows,col,Array.ofDim[String](rows,col).map(r=>r.map(c=>Cell(" "))),List())
  }

  def addShipOnGrid(x:Char,y:Int,sense:Char,ship: Ship): Grid ={
      val xInt=letterToNumber(x)
      if (!checkInput(xInt,y,sense,this.rows,this.columns)) throw new Exception("Invalid inputs (positions or sense value)")
      val futureCellsShip=this.futureCellsOfShip(xInt,y,sense,ship.numberOfCell,Array((x,y)))
      //test position possible bateau

      futureCellsShip.foreach(t=>{
        if(t._1 < 0||t._2 < 0||t._2 >= this.rows||t._1 >= this.columns) throw new Exception("Ship is outside the grid")
        if(grid(x)(y)!=Cell(" ")) throw new Exception("Cell already used")
      })
      val newGrid=this.updateGrid(this.grid,futureCellsShip,Cell(ship.id.toString))
      this.copy(grid=newGrid,ships = this.ships:+ship)

  }

  def updateGrid(grid:Array[Array[Cell]], cellsToUpdate:Array[(Int,Int)], newCell: Cell): Array[Array[Cell]] ={
    if (cellsToUpdate.length==0) grid
    else {
      val coordCellUpdate=cellsToUpdate.head
      val newGrid=Array.ofDim[Cell](this.rows,this.columns)
      grid.zipWithIndex.foreach{
        case(row,i) => {
          if(i == coordCellUpdate._2) {
            row.zipWithIndex.foreach{
              case(value, j) => {
                if(j == coordCellUpdate._1) {
                  newGrid(j)(i) = newCell
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
    if(this.grid(x)(y)!=Cell(" ")) {

      if(this.grid(x)(y).value=="H"||this.grid(x)(y).value=="X") {

        ("Already hit",None, this.copy())
      } else {
        val newGrid = this.updateGrid(this.grid, Array((x,y)), this.grid(x)(y).copy(value = "H"))
        val hitShip = this.ships.filter(_.id == this.grid(x)(y).value).head.isTouch()
        val newShips = this.ships.filterNot(_.id == this.grid(x)(y).value) :+ hitShip

        (if(hitShip.isSunk()) "Sunk" else "Hit",Some(hitShip),this.copy(ships=newShips,grid=newGrid))
      }

    } else {

      val newGrid = this.updateGrid(this.grid, Array((x,y)), this.grid(x)(y).copy(value = "X"))
      ("Miss Shot",None, this.copy(grid=newGrid))
    }
  }

  def letterToNumber(char: Char): Int = {
    char match {
      case 'A' | 'a' => 1
      case 'B' | 'b' => 2
      case 'C' | 'c' => 3
      case 'D' | 'd' => 4
      case 'E' | 'e' => 5
      case 'F' | 'f' => 6
      case 'I' | 'i' => 7
      case 'J' | 'j' => 8
      case 'K' | 'k' => 9
      case 'L' | 'l' => 10
      case _ => throw new Exception("bad value")

    }
  }

  def checkInput(x:Int, y:Int, sense:Char,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1 || sense!='h'||sense!='v') false
    else true
  }


}
