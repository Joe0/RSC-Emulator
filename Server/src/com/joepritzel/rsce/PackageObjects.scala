package com.joepritzel

package object rsce {
  val Logger = log.Logger
}

package rsce {
  
  package object alert {
     val Logger = log.Logger
  }

  package object event {
    val Logger = log.Logger
  }

  package event {
    package object impl {
      type Event[T] = _root_.com.joepritzel.rsce.event.Event[T]
      type Packet = _root_.com.joepritzel.rsce.net.Packet
      type PacketBuilder = _root_.com.joepritzel.rsce.net.PacketBuilder
      val Logger = log.Logger
    }
  }

  package object model {
    val Logger = log.Logger
  }

  package object net {
    val Logger = log.Logger
  }

  package net {
    package object handler {
      val Logger = log.Logger
    }
  }

  package object persistence {
    val Logger = log.Logger
  }

  package persistence {
    package object entity {
      val Logger = log.Logger
    }
  }

  package object util {
    val Logger = log.Logger
  }
}

