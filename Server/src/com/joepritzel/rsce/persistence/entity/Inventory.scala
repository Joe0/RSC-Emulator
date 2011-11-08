package com.joepritzel.rsce.persistence.entity

import org.squeryl.KeyedEntity

/**
 * A class that represents a single item in a player's inventory.
 * 
 * @author Joe Pritzel
 */
class Inventory(name_ : String, item_ : Int, amount_ : Long, wearing_ : Boolean, slot_ : Int) extends KeyedEntity[String] {
	var id = name_
	var item = item_
	var amount = amount_
	var wearing = wearing_
}

/**
 * This object is for all transactions and queries involving player inventories.
 *
 * @author Joe Pritzel
 */
object Inventory {
  import com.joepritzel.rsce.model.Player
  import com.joepritzel.rsce.persistence._
  import org.squeryl.PrimitiveTypeMode._
  import org.squeryl.Query

  /**
   * Loads the inventory from the database.
   */
  def load(user: String): Option[Inventory] = {
    var result: Option[Inventory] = None
    transaction {
      result = Schema.inventory.lookup(user)
    }
    return result
  }

  /**
   * Updates a player's record using the one provided.
   */
  def save(p: Inventory) {
    transaction {
      Schema.inventory.update(p)
    }
  }
}
