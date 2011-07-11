import com.joepritzel.rsce.persistence._
import com.joepritzel.rsce.persistence.entity._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.squeryl.PrimitiveTypeMode._

class PersistenceTest extends FlatSpec with ShouldMatchers {

	Persistence.init

	"Persistence" should "be able to execute select queries" in {
		transaction {
			val queriedUser:Player = Schema.players.where(p => p.id === "test").single
			queriedUser.id should equal("test")
		}
	}
}
