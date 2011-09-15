import com.joepritzel.rsce.model.Player
import com.joepritzel.rsce.persistence._
import com.joepritzel.rsce.persistence.entity._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode._
import java.sql.Timestamp

class PlayerTest extends FlatSpec with ShouldMatchers {

  Persistence.init
  val pd = new PlayerData("test", "test@gmail.com", "d653ea7ea31e77b41041e7e3d32e3e4a", "salt", "07e007fe5f99ee5851dd519bf6163a0d2dda54d45e6fe0127824f5b45a5ec59183a08aaa270979deb2f048815d05066c306e3694473d84d6aca0825c3dccd559", "salt", 777, 777, 0, new Timestamp(System.currentTimeMillis), "192.168.0.1", false, false, false, false, false, false, false, false)
  val ps = new PlayerStats("test", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36)
  val i = new Inventory("test", 0, 1000000, true, 0)

  "Player" should "be able to set itself up" in {
    transaction {
      Schema.players.insert(pd)
      Schema.stats.insert(ps)
      Schema.inventory.insert(i)
    }
    var result: Option[PlayerData] = None
    var result1: Option[PlayerStats] = None
    var result2: Option[Inventory] = None
    transaction {
      result = Schema.players.lookup("test")
      result1 = Schema.stats.lookup("test")
      result2 = Schema.inventory.lookup("test")
    }
    result should equal(Some(pd))
    result1 should equal(Some(ps))
    result2 should equal(Some(i))
  }

  "Player" should "be able to load players based on basic info." in {
    var p = new Player
    p.load("test", "test", false) should equal(true)

    // ensure data was actually loaded...
    p.playerData().x should (equal(777) or equal(778))
  }

  "Player" should "not load players when given an incorrect password" in {
    var p = new Player
    p.load("test", "invalid password", false) should equal(false)
    p.dispose
  }

  "Player" should "be able to clean up after itself" in {
    var result: Option[PlayerData] = Some(pd)
    var result1: Option[PlayerStats] = Some(ps)
    var result2: Option[Inventory] = Some(i)
    transaction {
      Schema.players.deleteWhere(p => p.id === "test")
      Schema.stats.deleteWhere(p => p.id === "test")
      Schema.inventory.deleteWhere(p => p.id === "test")
      result = Schema.players.lookup("test")
      result1 = Schema.stats.lookup("test")
      result2 = Schema.inventory.lookup("test")
    }
    result should equal(None)
    result1 should equal(None)
    result2 should equal(None)
  }
}
