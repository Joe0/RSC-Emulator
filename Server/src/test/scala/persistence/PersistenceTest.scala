import com.joepritzel.rsce.persistence._
import com.joepritzel.rsce.persistence.entity._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode._
import java.sql.Timestamp

class PersistenceTest extends FlatSpec with ShouldMatchers {

  Persistence.init
  val pd = new PlayerData("test", "test@gmail.com", "d653ea7ea31e77b41041e7e3d32e3e4a", "salt", "07e007fe5f99ee5851dd519bf6163a0d2dda54d45e6fe0127824f5b45a5ec59183a08aaa270979deb2f048815d05066c306e3694473d84d6aca0825c3dccd559", "salt", 777, 777, 0, new Timestamp(System.currentTimeMillis), "192.168.0.1", false, false, false, false, false, false, false, false)

  "Persistence" should "be able to insert" in {
    var result: PlayerData = null
    transaction {
      result = Schema.players.insert(pd)
    }
    result should equal(pd)
  }

  "Persistence" should "be able to execute select queries" in {
    transaction {
      val queriedUser: PlayerData = Schema.players.where(p => p.id === "test").single
      queriedUser.id should equal("test")
    }
  }

  var p: PlayerData = null
  "Persistence" should "be able to load a player" in {
    p = loadTestPlayer
    p.id should equal("test")
  }

  "Persistence" should "be able to save a player" in {
    var value = 777
    if (p.x == 777)
      value = 778
    p.x = value
    PlayerData.save(p)
    p = loadTestPlayer
    p.x should equal(value)
  }

  "Persistence" should "be able to delete a player" in {
    var result: Option[PlayerData] = Some(pd)
    transaction {
      Schema.players.deleteWhere(p => p.id === "test")
      result = Schema.players.lookup("test")
    }
    result should equal(None)
  }

  def loadTestPlayer = {
    PlayerData.load("test", "d653ea7ea31e77b41041e7e3d32e3e4a", "07e007fe5f99ee5851dd519bf6163a0d2dda54d45e6fe0127824f5b45a5ec59183a08aaa270979deb2f048815d05066c306e3694473d84d6aca0825c3dccd559")
  }
}
