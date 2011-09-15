package com.joepritzel.rsce.persistence.entity

import org.squeryl.KeyedEntity

class PlayerStats(name_ : String, attackExp_ : Int, defenseExp_ : Int, strengthExp_ : Int, hpExp_ : Int, rangedExp_ : Int, prayerExp_ : Int,
  magicExp_ : Int, cookingExp_ : Int, woodcutExp_ : Int, fletchingExp_ : Int, fishingExp_ : Int, firemakingExp_ : Int, craftingExp_ : Int, smithingExp_ : Int,
  miningExp_ : Int, herblawExp_ : Int, agilityExp_ : Int, thievingExp_ : Int, attack_ : Int, defense_ : Int, strength_ : Int, hp_ : Int,
  ranged_ : Int, prayer_ : Int, magic_ : Int, cooking_ : Int, woodcut_ : Int, fletching_ : Int, fishing_ : Int, firemaking_ : Int, crafting_ : Int,
  smithing_ : Int, mining_ : Int, herblaw_ : Int, agility_ : Int, thieving_ : Int) extends KeyedEntity[String] {
  var id = name_

  var attackExp = attackExp_
  var defenseExp = defenseExp_
  var strengthExp = strengthExp_
  var hpExp = hpExp_
  var rangedExp = rangedExp_
  var prayerExp = prayerExp_
  var magicExp = magicExp_
  var cookingExp = cookingExp_
  var woodcutExp = woodcutExp_
  var fletchingExp = fletchingExp_
  var fishingExp = fishingExp_
  var firemakingExp = firemakingExp_
  var craftingExp = craftingExp_
  var smithingExp = smithingExp_
  var miningExp = miningExp_
  var herblawExp = herblawExp_
  var agilityExp = agilityExp_
  var thievingExp = thievingExp_

  var attack = attack_
  var defense = defense_
  var strength = strength_
  var hp = hp_
  var ranged = ranged_
  var prayer = prayer_
  var magic = magic_
  var cooking = cooking_
  var woodcut = woodcut_
  var fletching = fletching_
  var fishing = fishing_
  var firemaking = firemaking_
  var crafting = crafting_
  var smithing = smithing_
  var mining = mining_
  var herblaw = herblaw_
  var agility = agility_
  var thieving = thieving_

  def setExp(id: Int, value: Int) {

  }
}

/**
 * This object is for all transactions and queries involving player stats.
 *
 * @author Joe Pritzel
 */
object PlayerStats {
  import com.joepritzel.rsce.model.Player
  import com.joepritzel.rsce.persistence._
  import org.squeryl.PrimitiveTypeMode._
  import org.squeryl.Query

  def load(user: String): Option[PlayerStats] = {
    var result: Option[PlayerStats] = None
    transaction {
      result = Schema.stats.lookup(user)
    }
    return result
  }

  /**
   * Updates a player's record using the one provided.
   */
  def save(p: PlayerStats) {
    transaction {
      Schema.stats.update(p)
    }
  }
}
