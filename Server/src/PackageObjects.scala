package com.joepritzel

package object rsce {
	val Logger = com.joepritzel.rsce.log.Logger
}

package rsce {

	package object event {
		val Logger = com.joepritzel.rsce.log.Logger
	}	

	package event {
		package object impl {
			val Logger = com.joepritzel.rsce.log.Logger
		}
	}

	package object model {
		val Logger = com.joepritzel.rsce.log.Logger
	}

	package object net {
		val Logger = com.joepritzel.rsce.log.Logger
	}

	package net {
		package object codec {
			val Logger = com.joepritzel.rsce.log.Logger
		}
	}

	package object persistence {
		val Logger = com.joepritzel.rsce.log.Logger
	}

	package persistence {
		package object entity {
			val Logger = com.joepritzel.rsce.log.Logger
		}
	}

	package object util {
		val Logger = com.joepritzel.rsce.log.Logger
	}
}
