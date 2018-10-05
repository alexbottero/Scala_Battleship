import scala.util.Random

/**
  * Created by alexandre on 03/10/2018.
  */

object Battleship extends App{


  val TYPE_OF_GAME=List("1: Human vs Human","2: Human vs IA level 1","3: Human vs IA level 2","4: Human vs IA level 3")
  val HEIGHT_GRID=10
  val LENGTH_GRID=10

  initLoop()


  def initLoop(): Unit = {
    println("--------- BattleShip----------")
    val typeG = menu()
    //Ship(3,3,3),Ship(4,4,4), Ship(5,5,5)
    val ships=List(Ship(1,2,2),Ship(2,3,3))
    typeG match {
      case 1=>{
        val players = getNamePlayers(typeG)
        val player1=HumanPlayer(name=players._1,new Grid(HEIGHT_GRID,LENGTH_GRID))
        val player2=HumanPlayer(name = players._2,new Grid(HEIGHT_GRID,LENGTH_GRID))
        print(player1.grid.displayGrid())

        val playersWithShips1=placeShips(ships,player1)
        val playersWithShips2=placeShips(ships,player2)

        println(playersWithShips1.getName()+ " Start the game")
        gameLoop(Game(playersWithShips1,playersWithShips2,playersWithShips1))



      }
      case _=>{
        val players = getNamePlayers(typeG)
        val player1=HumanPlayer(name=players._1,new Grid(HEIGHT_GRID,LENGTH_GRID))


        val player2=typeG match {
          case 2=>AILevel1(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
          case 3=> AILevel2(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
          case 4=> AILevel1(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
        }
        val playersWithShips1=placeShips(ships,player1)
        val playersWithShips2=placeShips(ships,player2)
        println(playersWithShips1.getName()+ " Start the game")
        gameLoop(Game(playersWithShips1,playersWithShips2,playersWithShips1))
      }


    }

  }


  def gameLoop(game: Game):Unit ={

    val player=game.p1
    val enemyPlayer=game.p2
    println(player.getName+" is your turn")
    val shoot=player.play()
    println("Your shoot is :" + shoot._1+" "+shoot._2)


    val result=enemyPlayer.getGrid().shootGrid(shoot._2,shoot._1)
    println(result._1)
    val newShots=player.getShots():+(shoot._1,shoot._2,result._1)
    val enemyPlayerUpdated=enemyPlayer.copyForGrid(grid=result._3)
    val playerUpdated=player.copyForShots(shots=newShots)

    println(enemyPlayerUpdated.getGrid().displayGridShot())
    println(playerUpdated.getGrid().displayGrid())


    if(enemyPlayerUpdated.loose()){
      println(player.getName() +" Win")
      endLoop(Game(enemyPlayer,enemyPlayerUpdated,player))
    }
    else {
      gameLoop(Game(enemyPlayerUpdated,playerUpdated,player))
    }
  }


  def placeShips(ships:List[Ship],player:Player):Player={
    if(ships.isEmpty)player
    else {
      println(player.getName()+" place the ship of length "+ships.head.numberOfCell)
      val updatePlayer=player.placeShip(ships.head)
      placeShips(ships.tail,updatePlayer)
    }
  }



  def menu():Int={
    println("Choose you type of game:")
    TYPE_OF_GAME.foreach({
      case (x) => println(x)
    })
    try{
      val typeG=scala.io.StdIn.readInt()
      if (typeG<1 || typeG>TYPE_OF_GAME.length) menu()
      else typeG
    }
    catch {
      case _:Exception=>menu()
    }
  }

  def getNamePlayers(typeOfGame:Int):(String,String)={
    typeOfGame match{
      case 1=> {
        println("Enter name Player 1")
        val namePlayer1=scala.io.StdIn.readLine()
        println("Enter name Player 2")
        val namePlayer2=scala.io.StdIn.readLine()
        (namePlayer1,namePlayer2)
      }
      case 2|3|4=>{
        println("Enter name Player 1")
        val namePlayer1=scala.io.StdIn.readLine()
        (namePlayer1,"AI")
      }
    }
  }

  def endLoop(game: Game): Unit ={
    print("End of game")
  }

}

