package com.joepritzel.rsce.util

import java.security.MessageDigest

object Hash {
	private def encrypt(s: String, algo: String) = {
    val md = MessageDigest.getInstance(algo)
    md.reset
    md.update(s.getBytes)
    md.digest.map(0xFF & _).map { "%02x".format(_) }.foldLeft("") { _ + _ }
  }

	def md5(s: String, salt: String = "") = {
		encrypt(salt + s, "MD5")
	}

	def sha512(s: String, salt: String = "") = {
		encrypt(salt + s, "SHA-512")
	}
}
