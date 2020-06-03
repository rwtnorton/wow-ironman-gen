/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

package wow_ironman_gen

import scala.util.Random

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
// orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

object Sampler {
  def sample[T](vs: Seq[T]): T = Random.shuffle(vs).head
}

trait SeqableEnum extends Enumeration {
  def toSeq: Seq[Value] = values.toSeq
}

trait SamplableEnum extends SeqableEnum {
  def sample: Value = Sampler.sample[Value](toSeq)
}

object Cls extends SamplableEnum {
  type Cls = Value
  val warrior, paladin, hunter, shaman, druid,
    rogue, monk, mage, warlock, priest = Value
}
import Cls._

object Race extends SamplableEnum {
  type Race = Value
  val human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
    orc, troll, forsaken, tauren, blood_elf, goblin = Value
}
import Race._

object Gender extends SamplableEnum {
  type Gender = Value
  val female, male = Value
}
import Gender._

object Faction extends SamplableEnum {
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
  def apply(r: Race): Set[wow_ironman_gen.Cls.Value] = classesForRace(r)
}

case class Toon(
  race: Race,
  cls: Cls,
  gender: Gender,
  faction: Faction) {

  val shouldShowFaction: Boolean = race == pandaren

  private[this] val attrs =
    if (shouldShowFaction)
      List(gender, faction, race, cls)
    else
      List(gender, race, cls)

  val showableAttrs: Seq[String] = attrs map (_.toString())

  override def toString: String = showableAttrs mkString " "
}

object Toon {
  def generateRandom: Toon = {
    val r = Race.sample
    val c = Sampler.sample(ClsMapper(r).toSeq)
    val g = Gender.sample
    val f = Faction.sample
    Toon(r, c, g, f)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    println(Toon.generateRandom)
  }
}
