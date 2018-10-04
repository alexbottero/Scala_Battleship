/**
  * Created by alexandre on 01/10/2018.
  */
abstract case class Player(name:String, grid: Grid,shots:List[(Int,Int,String)]=List()) {

  def placeShip(ship: Ship):Player
  def play():(Int,Int)

  def loose():Boolean={
    if (this.grid.ships.forall(s=>s.isSunk()))true else false
  }
  def checkInput(x:Int, y:Int,rows:Int,columns:Int):Boolean={

    if(y <0 || y>rows-1|| x <0 ||x>columns-1) false
    else true
  }


  /*def placeShip(player: Player,ship: Ship): Player ={
    if (!player.lapShips(ship,player)) player.copy(ships=player.ships:+ship)
    else player

  }
  def playerLoose(player: Player):Boolean=player.ships.isEmpty

  def add
  def removeShip(ship: Ship,player: Player):Player=player.copy(ships=player.ships.filter(_!=ship))

  def lapShips(ship: Ship, player: Player): Boolean = {
    if (player.ships.isEmpty) false
    else if (ship.lapShip(ship,player.ships.head)) true
    else lapShips(ship,player.copy(ships=player.ships.tail))
  }
*/

}
