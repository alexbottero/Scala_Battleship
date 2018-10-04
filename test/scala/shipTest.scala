import org.scalatest.FunSuite

/**
  * Created by alexandre on 04/10/2018.
  */
class shipTest extends FunSuite{
  test("IsSunk: return true if cellNotTouch==0"){
    assert(new Ship(1,5,0).isSunk())
  }
  test("IsSunk: after being touch the ship must be sunk"){
    assert(new Ship(1,5,1).touch().isSunk())
  }
  test("touch: after being touch cellNotTouch have to be cellNotTouch-1"){
    val ship=new Ship(1,5,2)
    assert(ship.touch().cellNotTouch==ship.cellNotTouch-1)
  }
  test("touch: Touch a ship with 0 cell not touch should return cellNotTouch 0"){
    assert(new Ship(1,5,0).touch.cellNotTouch==0)
  }

}
