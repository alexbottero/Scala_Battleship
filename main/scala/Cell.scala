/**
  * Created by alexandre on 01/10/2018.
  */
case class Cell(val x:Int, val y:Int, val value:String=" "){
  override def toString: String = " | "+value+" | "

  def stateCell():String= value match{
    case "O"=> "ship"
    case "X"=> "touch"
    case _=> "water"
  }


}
