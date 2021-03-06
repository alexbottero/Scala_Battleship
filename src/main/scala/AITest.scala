
import java.io.{BufferedWriter, FileWriter}


import scala.util.Random


/**
  * Created by alexandre on 05/10/2018.
  */
object AITest extends App{
  val HEIGHT_GRID=10
  val LENGTH_GRID=10
  val NB_GAME=10
  var winPlayer1=0
  var winPlayer2=0
  var result=""

  println("--------- Test AI----------")
  println("AI1 vs AI2")
  TestAI1vsAI2Loop(100)
  println("AI1 vs AI3")
  TestAI1vsAI3Loop(100)
  println("AI2 vs AI3")
  TestAI2vsAI3Loop(100)
  fileCsv(result)


  def TestAI1vsAI2Loop(nbGame:Int): Unit = {
    val typeGame="AI1vsAI2"
    if (nbGame==0){
      result+="AI Level Beginner;"+winPlayer1+";AI Level Medium;"+winPlayer2+";\n"
      winPlayer1=0
      winPlayer2=0
    }else{
      val ships=List(Ship(1,2,2),Ship(2,3,3),Ship(3,3,3),Ship(4,4,4), Ship(5,5,5))

      val player1=AILevel1(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val player2=AILevel2(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val playersWithShips1=placeShips(ships,player1)

      val playersWithShips2=placeShips(ships,player2)
      if(nbGame%2==0){
        gameLoop(Game(playersWithShips1,playersWithShips2),nbGame,typeGame)
      }
      else{
        gameLoop(Game(playersWithShips2,playersWithShips1),nbGame,typeGame)
      }
    }


  }

  def TestAI1vsAI3Loop(nbGame:Int): Unit = {
    val typeGame="AI1vsAI3"
    if (nbGame==0){

      result+="AI Level Beginner;"+winPlayer1+";AI Level Hard;"+winPlayer2+";\n"
      winPlayer1=0
      winPlayer2=0
    }else{
      val ships=List(Ship(1,2,2),Ship(2,3,3),Ship(3,3,3),Ship(4,4,4), Ship(5,5,5))

      val player1=AILevel1(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val player2=AILevel3(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val playersWithShips1=placeShips(ships,player1)
      val playersWithShips2=placeShips(ships,player2)
      if(nbGame%2==0){
        gameLoop(Game(playersWithShips1,playersWithShips2),nbGame,typeGame)
      }
      else{
        gameLoop(Game(playersWithShips2,playersWithShips1),nbGame,typeGame)
      }
    }


  }

  def TestAI2vsAI3Loop(nbGame:Int): Unit = {
    val typeGame="AI2vsAI3"
    if (nbGame==0){
      result+="AI Level Medium;"+winPlayer1+";AI Level Hard;"+winPlayer2+";\n"
      winPlayer1=0
      winPlayer2=0
    }else{

      val ships=List(Ship(1,2,2),Ship(2,3,3),Ship(3,3,3),Ship(4,4,4), Ship(5,5,5))

      val player1=AILevel2(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val player2=AILevel3(grid=new Grid(HEIGHT_GRID,LENGTH_GRID),random=new Random())
      val playersWithShips1=placeShips(ships,player1)
      val playersWithShips2=placeShips(ships,player2)
      if(nbGame%2==0){
        gameLoop(Game(playersWithShips1,playersWithShips2),nbGame,typeGame)
      }
      else{
        gameLoop(Game(playersWithShips2,playersWithShips1),nbGame,typeGame)
      }

    }


  }

  def gameLoop(game: Game,nbGame:Int,typeG:String):Unit ={

    val player=game.p1
    val enemyPlayer=game.p2
    val shoot=player.play()

    val result=enemyPlayer.getGrid().shootGrid(shoot._2,shoot._1)
    val newShots=player.getShots():+(shoot._1,shoot._2,result._1)

    val enemyPlayerUpdated=enemyPlayer.copyForGrid(grid=result._3)
    val playerUpdated=player.copyForShots(shots=newShots)

    if(enemyPlayerUpdated.loose()){
        endGame(Game(enemyPlayerUpdated,playerUpdated),nbGame,typeG)
    }
    else {
      gameLoop(Game(enemyPlayerUpdated,playerUpdated),nbGame,typeG)
    }
  }

  def endGame(game:Game,nbGame:Int,typeG:String): Unit ={
    typeG match{
      case "AI1vsAI2"=>{
        if(game.p1.getName()=="AI Level 1") winPlayer2+=1 else winPlayer1+=1
        TestAI1vsAI2Loop(nbGame-1)
      }
      case "AI1vsAI3"=>{
        if(game.p1.getName()=="AI Level 1") winPlayer2+=1 else winPlayer1+=1
        TestAI1vsAI3Loop(nbGame-1)
      }
      case "AI2vsAI3"=>{
        if(game.p1.getName()=="AI Level 2") winPlayer2+=1 else winPlayer1+=1
        TestAI2vsAI3Loop(nbGame-1)

      }
    }
  }

  def placeShips(ships:List[Ship],player:Player):Player={
    if(ships.isEmpty)player
    else {
      val updatedPlayer=player.placeShip(ships.head)
      placeShips(ships.tail,updatedPlayer)
    }
  }

  def fileCsv(s:String): Unit ={
    val outputFile = new BufferedWriter(new FileWriter("ai_proof.csv"))
    outputFile.write(result)
    outputFile.close()
    result=""


  }

}
