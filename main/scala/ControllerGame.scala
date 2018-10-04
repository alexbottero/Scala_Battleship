import scala.util.Random

/**
  * Created by alexandre on 03/10/2018.
  */
/*
object ControllerGame extends App{

  // CONST
  val TYPE_OF_GAME=List("1: Human vs Human","2: Human vs IA","3: IA vs IA")
  val LENGTH_SHIP=List(2,3,3,4,5)
  val HEIGHT_GRID=10
  val LENGTH_GRID=10

  //INIT
  val output=Output
  val input=Input
  init()


  def init(): Unit = {
    output.display("--------- BattleShip----------")
    val typeG = typeGame()
    val players = getNamePlayers(typeG)
    val player1=Player(name=players._1)
    val player2=Player(name = players._2)
    val starter= (new Random).nextInt(2)

    val game=Game(player1,player2,starter,if (starter==0)player1 else player2)

    print(game)
  }

  def createFleet(player: Player,lengths:List[Int]):Player={
    val cell=getCoord()
    val sense=getSense()
    if (lengths.isEmpty) player
    else if (checkCoord(lengths.head,sense,cell,HEIGHT_GRID,LENGTH_GRID)){

    }

  }

  def getCoord():Cell={
        output.display("Enter column value (A to J")
        try{
            val x=numberToLetter(input.getChar())
            output.display("Enter line value (1 to 10")
            val y=input.getInt()
            Cell(x,y,'O')
          }
        catch {case _:Exception=>getCoord()}
  }
  def getSense():Char={
    try {
      val sense=input.getChar()
      if (sense!='h' && sense!='h') getSense()
      else sense
    }catch{
      case _:Exception=>getSense()
    }
  }

  def typeGame():Int={
    output.display("Choose you type of game:")
    TYPE_OF_GAME.foreach({
      case (x) => output.display(x)
    })
    try{
      val typeG=input.getInt()
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
        output.display("Enter name Player 1")
        val namePlayer1=input.getString()
        output.display("Enter name Player 2")
        val namePlayer2=input.getString()
        (namePlayer1,namePlayer2)
      }
      case 2=>{
        output.display("Enter name Player 1")
        val namePlayer1=input.getString()
        (namePlayer1,"AI")
      }
      case 3=>{ output.display("No need name for the 2 AI")
        ("AI1","AI2")
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
*/
