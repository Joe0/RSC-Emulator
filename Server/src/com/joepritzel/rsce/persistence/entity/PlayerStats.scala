package com.joepritzel.rsce.persistence.entity

import org.squeryl.KeyedEntity
import com.joepritzel.rsce.util.Stats

/**
 * The class used in the Schema for a player's stats.
 *
 * @author Joe Pritzel
 */
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

  /**
   * Returns the stat level based purely on the amount of experience in the specified stat.
   */
  def getRealStat(index: Int) = {
    Stats.expToLevel(getExp(index))
  }

  /**
   * Sets the amount of experience in the specified stat.
   */
  def setExp(id: Int, value: Int) {
    applyExp(id, { e: Int => value })
  }

  /**
   * Sets the current level of the specified stat.
   */
  def setStat(id: Int, value: Int) {
    applyStat(id, { e: Int => value })
  }

  /**
   * Returns the current level of the specified stat.
   */
  def getStat(id: Int) = {
    id match {
      case 0 => attack
      case 1 => defense
      case 2 => strength
      case 3 => hp
      case 4 => ranged
      case 5 => prayer
      case 6 => magic
      case 7 => cooking
      case 8 => woodcut
      case 9 => fletching
      case 10 => fishing
      case 11 => firemaking
      case 12 => crafting
      case 13 => smithing
      case 14 => mining
      case 15 => herblaw
      case 16 => agility
      case 17 => thieving
    }
  }

  /**
   * Returns the amount of experience for the specified stat.
   */
  def getExp(id: Int) = {
    id match {
      case 0 => attackExp
      case 1 => defenseExp
      case 2 => strengthExp
      case 3 => hpExp
      case 4 => rangedExp
      case 5 => prayerExp
      case 6 => magicExp
      case 7 => cookingExp
      case 8 => woodcutExp
      case 9 => fletchingExp
      case 10 => fishingExp
      case 11 => firemakingExp
      case 12 => craftingExp
      case 13 => smithingExp
      case 14 => miningExp
      case 15 => herblawExp
      case 16 => agilityExp
      case 17 => thievingExp
    }
  }

  /**
   * Applies a function to the specified stat, and returns the value.
   */
  private def applyStat(id: Int, f: (Int) => Int) {
    id match {
      case 0 => attack = f(attack)
      case 1 => defense = f(defense)
      case 2 => strength = f(strength)
      case 3 => hp = f(hp)
      case 4 => ranged = f(ranged)
      case 5 => prayer = f(prayer)
      case 6 => magic = f(magic)
      case 7 => cooking = f(cooking)
      case 8 => woodcut = f(woodcut)
      case 9 => fletching = f(fletching)
      case 10 => fishing = f(fishing)
      case 11 => firemaking = f(firemaking)
      case 12 => crafting = f(crafting)
      case 13 => smithing = f(smithing)
      case 14 => mining = f(mining)
      case 15 => herblaw = f(herblaw)
      case 16 => agility = f(agility)
      case 17 => thieving = f(thieving)
    }
  }

  /**
   * Applies a function to the specified stat's experience and returns the value.
   */
  private def applyExp(id: Int, f: (Int) => Int) {
    id match {
      case 0 => attackExp = f(attackExp)
      case 1 => defenseExp = f(defenseExp)
      case 2 => strengthExp = f(strengthExp)
      case 3 => hpExp = f(hpExp)
      case 4 => rangedExp = f(rangedExp)
      case 5 => prayerExp = f(prayerExp)
      case 6 => magicExp = f(magicExp)
      case 7 => cookingExp = f(cookingExp)
      case 8 => woodcutExp = f(woodcutExp)
      case 9 => fletchingExp = f(fletchingExp)
      case 10 => fishingExp = f(fishingExp)
      case 11 => firemakingExp = f(firemakingExp)
      case 12 => craftingExp = f(craftingExp)
      case 13 => smithingExp = f(smithingExp)
      case 14 => miningExp = f(miningExp)
      case 15 => herblawExp = f(herblawExp)
      case 16 => agilityExp = f(agilityExp)
      case 17 => thievingExp = f(thievingExp)
    }
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

  /**
   * Loads a user's stats from the database.
   */
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
