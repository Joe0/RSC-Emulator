package rsce.entity

import javax.inject.Provider
import rsce.entity.traits.{ Persistent, Networked, Composable }

class Player extends Entity with Composable with Networked with Persistent {
}

class PlayerProvider extends Provider[Player] {
  override def get = {
    new Player
  }
}