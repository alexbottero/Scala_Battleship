/**
  * Created by alexandre on 01/10/2018.
  */



case class Cell(x:Int, y:Int, value:Char=' '){

  def changeState(cell: Cell,newValue:Char):Cell = cell.copy(value=newValue)

}
