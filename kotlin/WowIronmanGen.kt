package wow_ironman_gen

//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest


fun main(args: Array<String>) {
    println("hello")
    val toon = Toon(
        faction = Faction.Horde,
        race = Race.Forsaken,
        gender = Gender.Female,
        wowClass = WowClass.Warlock
    )
    println(toon)
    val toon2 = Toon(
        faction = Faction.Alliance,
        race = Race.Pandaren,
        gender = Gender.Male,
        wowClass = WowClass.Shaman
    )
    println(toon2)
    val toon3 = Toon(
        faction = Faction.Alliance,
        race = Race.NightElf,
        gender = Gender.Female,
        wowClass = WowClass.Hunter
    )
    println(toon3)
}

enum class WowClass {
    Warrior, Paladin, Hunter, Shaman, Druid, Rogue, Monk, Mage, Warlock, Priest;

    override fun toString() = when (this) {
        Warrior -> "warrior"
        Paladin -> "paladin"
        Hunter -> "hunter"
        Shaman -> "shaman"
        Druid -> "druid"
        Rogue -> "rogue"
        Monk -> "monk"
        Mage -> "mage"
        Warlock -> "warlock"
        Priest -> "priest"
    }
}

enum class Race {
    Human,
    NightElf,
    Dwarf,
    Gnome,
    Draenei,
    Worgen,
    Pandaren,
    Orc,
    Troll,
    Forsaken,
    Tauren,
    BloodElf,
    Goblin;

    override fun toString() = when (this) {
        Human -> "human"
        NightElf -> "night_elf"
        Dwarf -> "dwarf"
        Gnome -> "gnome"
        Draenei -> "draenei"
        Worgen -> "worgen"
        Pandaren -> "pandaren"
        Orc -> "orc"
        Troll -> "troll"
        Forsaken -> "forsaken"
        Tauren -> "tauren"
        BloodElf -> "blood_elf"
        Goblin -> "goblin"
    }
}

enum class Faction {
    Horde, Alliance;

    override fun toString() = when (this) {
        Horde -> "horde"
        Alliance -> "alliance"
    }
}
enum class Gender {
    Female, Male;

    override fun toString() = when (this) {
        Female -> "female"
        Male -> "male"
    }
}

data class Toon(
    val race: Race,
    val wowClass: WowClass,
    val faction: Faction,
    val gender: Gender
) {
    override fun toString() = attributes.joinToString(separator = " ")

    private val attributes = if (race == Race.Pandaren)
            listOf(gender, faction, race, wowClass)
        else
            listOf(gender, race, wowClass)
}

/*
my %classes_for_race = (
    human => [qw[warrior paladin hunter rogue monk mage warlock priest]],
    night_elf => [qw[warrior hunter rogue druid monk mage priest]],
    dwarf => [qw[warrior paladin hunter shaman rogue monk mage warlock priest]],
    gnome => [qw[warrior rogue monk mage warlock priest]],
    draenei => [qw[warrior paladin hunter shaman monk mage priest]],
    worgen => [qw[warrior hunter druid rogue mage warlock priest]],
    pandaren => [qw[warrior hunter shaman rogue monk mage priest]],
    orc => [qw[warrior hunter shaman rogue monk mage warlock]],
    troll => [qw[warrior hunter shaman druid rogue monk mage warlock priest]],
    forsaken => [qw[warrior hunter rogue monk mage warlock priest]],
    tauren => [qw[warrior paladin hunter shaman druid monk priest]],
    blood_elf => [qw[warrior paladin hunter rogue monk mage warlock priest]],
    goblin => [qw[warrior hunter shaman rogue mage warlock priest]],
    );
*/
