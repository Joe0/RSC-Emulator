package com.joepritzel.rsce.util

import java.security.MessageDigest

/**
 * This object is used to quickly hash things with various algorithms.
 * 
 * @author Joe Pritzel
 */
object Hash {
	
	/**
	 * This method returns the digest of a string, when given a specified algorithm.
	 */
	private def hash(s: String, algo: String) = {
		val md = MessageDigest.getInstance(algo)
		md.reset
		md.update(s.getBytes)
		md.digest.map(0xFF & _).map { "%02x".format(_) }.foldLeft("") { _ + _ }
	}

	/**
	 * This method returns the MD5 hash of a string.<br>
	 * If a salt is provided, it prepends it to the string.
	 */
	def md5(s: String, salt: String = "") = {
		hash(salt + s, "MD5")
	}

	/**
	 * This method returns the SHA-512 hash of a string.<br>
	 * If a salt is provided, it prepends it to the string.
	 */
	def sha512(s: String, salt: String = "") = {
		hash(salt + s, "SHA-512")
	}
}
