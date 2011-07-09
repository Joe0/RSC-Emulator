package info.javacoding.rscemulator.server.util
import java.math.BigInteger
import java.security.spec.{ PKCS8EncodedKeySpec, X509EncodedKeySpec }
import java.security.{ Key, KeyFactory, KeyPairGenerator, PrivateKey, PublicKey, SecureRandom }
import javax.crypto.Cipher

/**
 * This object provides a secure way to generate RSA key pairs, encrypt, and decrypt messages using RSA/ECB/PKCSPadding.
 *
 * @author Joe Pritzel
 */
object RSA {

  def generateKeyPair = {
    val sr = new SecureRandom()
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    val generator = KeyPairGenerator.getInstance("RSA")
    generator.initialize(1024, sr)
    val pair = generator.genKeyPair
    (pair.getPublic, pair.getPrivate)
  }

  def encrypt(input: Array[Byte], key: Key) = {
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    val sr = new SecureRandom()
    cipher.init(Cipher.ENCRYPT_MODE, key, sr)
    cipher.doFinal(input)
  }

  def decrypt(cipherText: Array[Byte], key: Key) = {
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    val sr = new SecureRandom()
    cipher.init(Cipher.DECRYPT_MODE, key, sr)
    cipher.doFinal(cipherText)
  }
}

/**
 * This object provides an insecure way to encrypt and decrypt messages using RSA/ECB/PKCSPadding.<br>
 * This is only here to keep compadibility with the RSCA client.
 *
 * @author Joe Pritzel
 */
object InsecureRSA {
  private val keyFactory = KeyFactory.getInstance("RSA")
  private val publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(new BigInteger("258483531987721813854435365666199783121097212864526576114955744050873252978581213214062885665119329089273296913884093898593877564098511382732309048889240854054459372263273672334107564088395710980478911359605768175143527864461996266529749955416370971506195317045377519645018157466830930794446490944537605962330090699836840861268493872513762630835769942133970804813091619416385064187784658945").toByteArray()))
  private val privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(new BigInteger("126389230230897930352385109045517175528919326976939639978192012327670621489047580094424093866394604083620710942258641479882605306217705080633073206358002116577711775977035174649355992840385149147227804978220431329886904749249047207172981994234190017310148273188617548379533657419013879671232142718169518429371391936849151606245205570182197305333898616362563500262673852314745836689909720746451418490347388427381974609081779036643692442896601636017446393710362921966444628494554137329105609231821252714960402427087902143625637826354987050179190296311361184976459578647089802255916487029562372894817902016349527418080110572755696829671001527629662007011502494795321796989748708894483748787746164007093796775322700601606206239680220934740393355136437625692864876018489463040975412867784876767858234777778613227623572162881295529316433265197827292214481807179049611685053128209907494051691218003161010138655935539925662842276881932027193524730598562717449099166747466602094321757382874332291191770626601705016723177033286335759178872988144726412991304849553921854275796353460611722080118921976660955130059428940619614317969278356912087565839497213220194655672243883744862866647835331423918525974671607339058850826043973690788036967549648769172496014048685165109400959786151179359607410101378890865847238149636112094448917842512853709764865360978231071734030322981843223939320472985117985802975969976688950242865772248639234517663026594659960052526081995926396802458422109485608674299402862528020107837865224663685601410678473927829").toByteArray()))

  def encrypt(input: Array[Byte]) = RSA.encrypt(input, privateKey)
  def decrypt(cipherText: Array[Byte]) = RSA.decrypt(cipherText, privateKey)
}