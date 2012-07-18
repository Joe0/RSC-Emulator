package rsce.entity

import rsce.entity.traits.Composable
import rsce.valueobject.Point
import rsce.entity.traits.Networked
import org.jboss.netty.channel.Channel
import javax.inject.Provider
import rsce.entity.traits.Persistent

class Player extends Entity with Composable with Networked with Persistent {
}

class PlayerProvider extends Provider[Player] {
  def get = {
    new Player
  }
}