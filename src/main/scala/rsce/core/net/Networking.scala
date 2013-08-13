package rsce.core.net

import javax.inject.Inject
import org.jboss.netty.bootstrap.ServerBootstrap
import java.net.SocketAddress

/**
 * This is the interface in which we use to start the networking portion of our
 * application. It will attempt to clean itself up when the server is shutting
 * down.
 *
 * @author Joe Pritzel
 *
 */
object Networking {

  /**
   * This is the bootstrap that will be used.
   */
  @Inject val bootstrap: ServerBootstrap = null

  /**
   * The is the socket address to bind the bootstrap to.
   */
  @Inject val address: SocketAddress = null

  /**
   * Starts the networking portion of the server.
   */
  def start {
    bootstrap.bind(address)

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run {
        bootstrap.releaseExternalResources
      }
    })
  }
}