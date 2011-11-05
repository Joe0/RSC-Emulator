import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.joepritzel.rsce.util.Hash


class HashTest extends FlatSpec with ShouldMatchers {

	"SHA-512" should "generate the correct hash" in {
		Hash.sha512("test") should equal("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff")
	}

	"SHA-512 w/ salt" should "hash 'salt + msg' and generate the correct hash" in {
		// Should hash "salttest"
		Hash.sha512("test", "salt") should equal("07e007fe5f99ee5851dd519bf6163a0d2dda54d45e6fe0127824f5b45a5ec59183a08aaa270979deb2f048815d05066c306e3694473d84d6aca0825c3dccd559")
	}

	"MD5" should "generate the correct hash" in {
		Hash.md5("test") should equal("098f6bcd4621d373cade4e832627b4f6")
	}

	"MD5 w/ salt" should "hash 'salt + msg' and generate the correct value" in {
		// Should hash "salttest"
		Hash.md5("test", "salt") should equal("d653ea7ea31e77b41041e7e3d32e3e4a")
	}
}
