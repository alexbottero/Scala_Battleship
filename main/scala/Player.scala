/**
  * Created by alexandre on 01/10/2018.
  */
case class Player(ships:List[Ship], name:String,shots:List[Cell]) {

  def placeShip(player: Player,ship: Ship): Player ={
    if (!player.lapShips(ship,player)) player.copy(ships=player.ships:+ship)
    else player

  }
  def playerLoose(player: Player):Boolean=player.ships.isEmpty

  def removeShip(ship: Ship,player: Player):Player=player.copy(ships=player.ships.filter(_!=ship))

  def lapShips(ship: Ship, player: Player): Boolean = {
    if (player.ships.isEmpty) false
    else if (ship.lapShip(ship,player.ships.head)) true
    else lapShips(ship,player.copy(ships=player.ships.tail))
  }


}
