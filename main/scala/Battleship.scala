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
    val ships=List(Ship(1,2,2),Ship(2,3,3),Ship(3,3,3),Ship(4,4,4), Ship(5,5,5))
    typeG match {
      case 1=>{
        val players = getNamePlayers(typeG)
        val player1=HumanPlayer(name=players._1,new Grid(HEIGHT_GRID,LENGTH_GRID))
        val player2=HumanPlayer(name = players._2,new Grid(HEIGHT_GRID,LENGTH_GRID))

        val playersWithShips1=placeShips(ships,player1)
        val playersWithShips2=placeShips(ships,player2)

        println(playersWithShips1.name+ " Start the game")
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
      }


    }

  }


  def gameLoop(game: Game): Game ={

    val player=game.p1
    val enemyPlayer=game.p2
    println(player.name+" is your turn")
    println(player.grid.displayMyGrid())
    println(player.grid.displayEnemyGrid())
    val shoot=player.play()
    println("Your shoot is :" + shoot._1+" "+shoot._2)

    val result =enemyPlayer.grid.shootGrid(shoot._1,shoot._2)
    println(result._1)
    val newShots=player.shots:+result
    val enemyPlayerUpdated=enemyPlayer.getClass.toString match {
      case "HumanPlayer"=> new HumanPlayer(enemyPlayer.name,result._3,newShots)
      case "AILevel1"=> new AILevel1(enemyPlayer,result._3,List())
      case "AILevel2"=> new AILevel2(enemyPlayer,result._3,newShots)
    }
    val PlayerUpdated=player.getClass.toString match {
      case "HumanPlayer"=> new HumanPlayer(enemyPlayer.name,result._3,newShots)
      case "AILevel1"=> new AILevel1(enemyPlayer,result._3,List())
      case "AILevel2"=> new AILevel2(enemyPlayer,result._3,newShots)
    }
    if(enemyPlayerUpdated.loose()){
      println(player.name +" Win")
    }
    else {
      gameLoop(new Game(enemyPlayer,enemyPlayerUpdated,player))
    }



    if(newPlayer1.didLose()){
      println(s"${newPlayer2.username} won the Game. Well Played!!!!")
    } else if(newPlayer2.didLose()){
      println(s"${newPlayer1.username} won the Game. Well Played!!!!")
    } else{
      gameLoop(GameState(newPlayer2, newPlayer1))
    }
  }


  def placeShips(ships:List[Ship],player:Player):Player={
    if(ships.isEmpty)player
    else {
      println(player.name+" Place the ship of length "+ships.head.numberOfCell)
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
      if (typeG<1 || typeG>TYPE_OF_GAME.length) typeGame()
      else typeG
    }
    catch {
      case _:Exception=>typeGame()
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

  def numberToLetter(char: Char): Int = {
    char match {
      case 'A'| 'a'=>1
      case 'B'| 'b'=>2
      case 'C'| 'c'=>3
      case 'D'| 'd'=>4
      case 'E'| 'e'=>5
      case 'F'| 'f'=>6
      case 'I'| 'i'=>7
      case 'J'| 'j'=>8
      case 'K'| 'k'=>9
      case 'L'| 'l'=>10
      case _=> throw new Exception("bad value")

    }
  }

  def checkCoord(lengthShip:Int,sense:Char,startCell:Cell,heightGrid:Int,lengthGrid:Int):Boolean={
    if(startCell.y <1 || startCell.y>heightGrid || startCell.x <1 ||startCell.x>lengthGrid) false
    else if(lengthShip==1)true
    else if (sense=='v') checkCoord(lengthShip-1,sense,startCell.copy(y=startCell.y+1),heightGrid,lengthGrid)
    else   checkCoord(lengthShip-1,sense,startCell.copy(x=startCell.x+1),heightGrid,lengthGrid)
  }

}

