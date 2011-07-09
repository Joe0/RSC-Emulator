package info.javacoding.rscemulator.server.model

/**
 * All NPCs, Players, and any other Entity should have this trait.
 *
 * @author Joe Pritzel
 */
trait Entity {
  /**
   * Called when an Entity is being destroyed.
   */
  def dispose
}