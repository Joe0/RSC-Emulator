package rsce.module

import rsce.entity.World
import rsce.event.listener.NetworkEventListenerBootstrap
import javax.inject.Inject

class Bootstraps {
  def bootstrap {
    @Inject var world : World = null
    new NetworkEventListenerBootstrap(world)
  }
}