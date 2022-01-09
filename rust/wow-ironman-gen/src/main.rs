//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest


fn main() {
    let toon = Toon { gender: Gender::Male, faction: Faction::Horde, race: Race::Orc, class: Class::Rogue };
    println!("toon = {:?}", toon);
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Gender { Female, Male }

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Faction { Horde, Alliance }

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Race {
    Human, NightElf, Dwarf, Gnome, Draenei, Worgen,
    Pandaren,
    Orc, Troll, Forsaken, Tauren, BloodElf, Goblin,
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Class {
    Warrior, Paladin, Hunter, Shaman, Druid,
    Rogue, Monk, Mage, Warlock, Priest,
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
struct Toon {
    race: Race,
    class: Class,
    gender: Gender,
    faction: Faction,
}

// impl fmt::Show for Toon {
    
// }
