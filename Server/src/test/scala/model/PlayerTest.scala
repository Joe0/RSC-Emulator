import com.joepritzel.rsce.model.Player
import com.joepritzel.rsce.persistence._
import com.joepritzel.rsce.persistence.entity._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class PlayerTest extends FlatSpec with ShouldMatchers {

	Persistence.init

	"Player" should "be able to load players based on basic info." in {
		var p = new Player
		p.load("test", "test", false) should equal(true)
		
		// ensure data was actually loaded...
		p.playerData().x should (equal (777) or equal (778))
	}

	"Player" should "not load players when given an incorrect password" in {
		var p = new Player
		p.load("test", "invalid password", false) should equal(false)
	}
}
