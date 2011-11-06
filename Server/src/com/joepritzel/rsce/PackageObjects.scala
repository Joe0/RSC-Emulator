package com.joepritzel

package object rsce {
  val Logger = log.Logger
}

package rsce {

  package object event {
    val Logger = log.Logger
  }

  package event {
    package object impl {
      type Event = _root_.com.joepritzel.rsce.event.Event
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

