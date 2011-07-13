import com.joepritzel.rsce.persistence._
import com.joepritzel.rsce.persistence.entity._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode._

class PersistenceTest extends FlatSpec with ShouldMatchers {

	Persistence.init

	"Persistence" should "be able to execute select queries" in {
		transaction {
			val queriedUser:PlayerData = Schema.players.where(p => p.id === "test").single
			queriedUser.id should equal("test")
		}
	}

	var p: PlayerData = null
	"Persistence" should "be able to load a player" in {
			p = loadTestPlayer
			p.id should equal ("test")
	}

	"Persistence" should "be able to save a player" in {
		var value = 777
		if(p.x == 777)
			value = 778
		p.x = value
		PlayerData.savePlayer(p)
		p = loadTestPlayer
		p.x should equal(value)
	}

	def loadTestPlayer = {
		PlayerData.loadPlayer("test", "7c295855be9762caccdf03069d0cc691", 			"dab001ecc95364cc197e27567858685e5ec860024e9466257ecec81e81bed85c5e27ad8ba7f30804e99bdb2945d1e866daaf367477d4f47639e5458f67a45a29")
	}
}
