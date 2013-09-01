/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

package scala_wow_ironman_gen

import scala.util.Random

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
// orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

object Sampler {
  def sample[T](vs: Seq[T]): T = Random.shuffle(vs).head
}

object Cls extends Enumeration {
  type Cls = Value
  val warrior, paladin, hunter, shaman, druid,
    rogue, monk, mage, warlock, priest = Value
}
import Cls._

object Race extends Enumeration {
  type Race = Value
  val human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
    orc, troll, forsaken, tauren, blood_elf, goblin = Value
}
import Race._

object Gender extends Enumeration {
  type Gender = Value
  val female, male = Value
}
import Gender._

object Faction extends Enumeration {
  type Faction = Value
  val horde, alliance = Value
}
import Faction._

object ClsMapper {
  val classesForRace = Map(
    human     -> Set(warrior, paladin, hunter, rogue, monk, mage, warlock, priest),
    night_elf -> Set(warrior, hunter, rogue, druid, monk, mage, priest),
    dwarf     -> Set(warrior, paladin, hunter, shaman, rogue, monk, mage, warlock, priest),
    gnome     -> Set(warrior, rogue, monk, mage, warlock, priest),
    draenei   -> Set(warrior, paladin, hunter, shaman, monk, mage, priest),
    worgen    -> Set(warrior, hunter, druid, rogue, mage, warlock, priest),
    pandaren  -> Set(warrior, hunter, shaman, rogue, monk, mage, priest),
    orc       -> Set(warrior, hunter, shaman, rogue, monk, mage, warlock),
    troll     -> Set(warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest),
    forsaken  -> Set(warrior, hunter, rogue, monk, mage, warlock, priest),
    tauren    -> Set(warrior, paladin, hunter, shaman, druid, monk, priest),
    blood_elf -> Set(warrior, paladin, hunter, rogue, monk, mage, warlock, priest),
    goblin    -> Set(warrior, hunter, shaman, rogue, mage, warlock, priest)
  )
  def apply(r: Race) = classesForRace(r)
}

case class Toon(
  val race: Race,
  val cls: Cls,
  val gender: Gender,
  val faction: Faction) {

  val shouldShowFaction = race == pandaren

  val showableAttrs: Array[String] =
    if (shouldShowFaction)
      Array(gender, faction, race, cls) map (_.toString)
    else
      Array(gender, race, cls) map (_.toString)

  override def toString(): String = showableAttrs mkString " "
}

object Toon {
  def generateRandom: Toon = {
    val r = Sampler.sample(Race.values.toSeq)
    val c = Sampler.sample(ClsMapper(r).toSeq)
    val g = Sampler.sample(Gender.values.toSeq)
    val f = Sampler.sample(Faction.values.toSeq)
    Toon(r, c, g, f)
  }
}

// $ cd path/to/wow_ironman_gen/dir
// $ scalac scala_wow_ironman_gen/Main.scala
// $ scala -cp . scala_wow_ironman_gen.Main

object Main {
  def main(args: Array[String]) {
    println(Toon.generateRandom)
  }
}
