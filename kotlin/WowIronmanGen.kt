package com.github.rwtnorton.wowironmangen

import kotlin.random.Random

//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

fun main(args: Array<String>) {
    println(Toon.random())
}

val rand = Random(System.currentTimeMillis())

fun <T> List<T>.sample(): T =
    if (isEmpty())
        throw IllegalStateException("cannot sample empty collection")
    else
        get(Math.abs(rand.nextInt() % size))

fun <T> Set<T>.sample(): T = toList().sample()

val camelCaseRegex = """([A-Z]?[a-z]+)""".toRegex()
fun String.toSnakeCase() =
    camelCaseRegex.findAll(this).toList().map {
        it.groupValues.first().toLowerCase()
    }.joinToString(separator = "_")

fun classesForRace(race: Race): Set<WowClass> = when (race) {
    Race.Human -> setOf(
        WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Rogue,
        WowClass.Monk, WowClass.Mage, WowClass.Warlock, WowClass.Priest
    )
    Race.NightElf -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Rogue, WowClass.Druid,
        WowClass.Monk, WowClass.Mage, WowClass.Priest
    )
    Race.Dwarf -> setOf(
        WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
        WowClass.Rogue, WowClass.Monk, WowClass.Mage, WowClass.Warlock,
        WowClass.Priest
    )
    Race.Gnome -> setOf(
        WowClass.Warrior, WowClass.Rogue, WowClass.Monk, WowClass.Mage,
        WowClass.Warlock, WowClass.Priest
    )
    Race.Draenei -> setOf(
        WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
        WowClass.Monk, WowClass.Mage, WowClass.Priest
    )
    Race.Worgen -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Druid, WowClass.Rogue,
        WowClass.Mage, WowClass.Warlock, WowClass.Priest
    )
    Race.Pandaren -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
        WowClass.Monk, WowClass.Mage, WowClass.Priest
    )
    Race.Orc -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
        WowClass.Monk, WowClass.Mage, WowClass.Warlock
    )
    Race.Troll -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Druid,
        WowClass.Rogue, WowClass.Monk, WowClass.Mage, WowClass.Warlock,
        WowClass.Priest
    )
    Race.Forsaken -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Rogue, WowClass.Monk,
        WowClass.Mage, WowClass.Warlock, WowClass.Priest
    )
    Race.Tauren -> setOf(
        WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Shaman,
        WowClass.Druid, WowClass.Monk, WowClass.Priest
    )
    Race.BloodElf -> setOf(
        WowClass.Warrior, WowClass.Paladin, WowClass.Hunter, WowClass.Rogue,
        WowClass.Monk, WowClass.Mage, WowClass.Warlock, WowClass.Priest
    )
    Race.Goblin -> setOf(
        WowClass.Warrior, WowClass.Hunter, WowClass.Shaman, WowClass.Rogue,
        WowClass.Mage, WowClass.Warlock, WowClass.Priest
    )
}

enum class WowClass {
    Warrior, Paladin, Hunter, Shaman, Druid, Rogue, Monk, Mage, Warlock, Priest;

    companion object {
        fun randomForRace(race: Race) = classesForRace(race).sample()
    }

    override fun toString() = name.toLowerCase()
}

enum class Race {
    Human, NightElf, Dwarf, Gnome, Draenei, Worgen,
    Pandaren,
    Orc, Troll, Forsaken, Tauren, BloodElf, Goblin;

    val isMultiFaction: Boolean
        get() = this == Pandaren

    val faction: Faction
        get() = when (this) {
            Human, NightElf, Dwarf, Gnome, Draenei, Worgen -> Faction.Alliance
            Pandaren -> Faction.random()
            Orc, Troll, Forsaken, Tauren, BloodElf, Goblin -> Faction.Horde
        }

    companion object {
        fun random() = values().toList().sample()
    }

    override fun toString() = name.toSnakeCase()
}

enum class Faction {
    Horde, Alliance;

    companion object {
        fun random() = values().toList().sample()
    }

    override fun toString() = name.toLowerCase()
}

enum class Gender {
    Female, Male;

    companion object {
        fun random() = values().toList().sample()
    }

    override fun toString() = name.toLowerCase()
}

data class Toon(
    val race: Race,
    val wowClass: WowClass,
    val faction: Faction,
    val gender: Gender = Gender.random()
) {
    override fun toString() = attributes.joinToString(separator = " ")

    private val attributes = if (race.isMultiFaction)
            listOf(gender, faction, race, wowClass)
        else
            listOf(gender, race, wowClass)

    companion object {
        fun random(): Toon {
            val r = Race.random()
            return Toon(
                race = r,
                faction = r.faction,
                wowClass = WowClass.randomForRace(r)
            )
        }
    }
}
