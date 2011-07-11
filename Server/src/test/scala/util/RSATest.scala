import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.joepritzel.rsce.util.{ RSA, InsecureRSA }
import scala.util.Random


class RSATest extends FlatSpec with ShouldMatchers {
	val keyPair = RSA.generateKeyPair

	"A RSA key pair" should "not be null" in {
		keyPair should not equal null
	}

	"A RSA key pair" should "be able to correctly encrypt and decrypt a Array[Byte]" in {
		val array = new Array[Byte](117)
		new Random().nextBytes(array)
		RSA.decrypt(RSA.encrypt(array, keyPair._1), keyPair._2).deep should equal (array.deep)
		RSA.decrypt(RSA.encrypt(array, keyPair._2), keyPair._1).deep should equal (array.deep)
	}

	"InsecureRSA" should "be able to correctly encrypt and decrypt a Array[Byte]" in {
		val array = new Array[Byte](117)
		new Random().nextBytes(array)
		InsecureRSA.encrypt(array).deep should equal (RSA.encrypt(array, InsecureRSA.privateKey).deep)
		val encrypted = RSA.encrypt(array, InsecureRSA.publicKey)
		InsecureRSA.decrypt(encrypted).deep should equal (RSA.decrypt(encrypted, InsecureRSA.privateKey).deep)
	}
}
