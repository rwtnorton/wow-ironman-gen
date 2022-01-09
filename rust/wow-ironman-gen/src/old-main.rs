//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

// use core::fmt::Show;
// extern crate core;

fn main() {
    let toon = Toon { gender: Male, faction: Horde, race: Orc, class: Rogue };
    println!("toon = {}", toon);
}

enum Gender { Female, Male }
enum Faction { Horde, Alliance }

enum Race {
    Human, NightElf, Dwarf, Gnome, Draenei, Worgen,
    Pandaren,
    Orc, Troll, Forsaken, Tauren, BloodElf, Goblin,
}

enum Class {
    Warrior, Paladin, Hunter, Shaman, Druid,
    Rogue, Monk, Mage, Warlock, Priest,
}

struct Toon {
    race: Race,
    class: Class,
    gender: Gender,
    faction: Faction,
}

impl fmt::Show for Toon {
    
}
