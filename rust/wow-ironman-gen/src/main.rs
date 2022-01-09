//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

use std::fmt;
use rand::{thread_rng, Rng};

fn main() {
    let mut rng = thread_rng();
    let x: u32 = rng.gen();
    println!("{}", x);
    let toon = Toon { gender: Gender::Male, faction: Faction::Horde, race: Race::Orc, class: Class::Rogue };
    println!("toon = {:?}", toon);
    println!("gender = {}", toon.gender);
    println!("faction = {}", toon.faction);
    println!("race = {}", toon.race);
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Gender { Female, Male }

impl fmt::Display for Gender {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        let s = match self {
            Gender::Female => "female",
            Gender::Male => "male",
        };
        write!(f, "{}", s)
    }
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Faction { Horde, Alliance }

impl fmt::Display for Faction {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        let s = match self {
            Faction::Alliance => "alliance",
            Faction::Horde => "horde",
        };
        write!(f, "{}", s)
    }
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Race {
    Human, NightElf, Dwarf, Gnome, Draenei, Worgen,
    Pandaren,
    Orc, Troll, Forsaken, Tauren, BloodElf, Goblin,
}

impl fmt::Display for Race {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        let s = match self {
            Race::BloodElf => "blood_elf",
            Race::Draenei => "draenei",
            Race::Dwarf => "dwarf",
            Race::Forsaken => "forsaken",
            Race::Gnome => "gnome",
            Race::Goblin => "goblin",
            Race::Human => "human",
            Race::NightElf => "night_elf",
            Race::Orc => "orc",
            Race::Pandaren => "pandaren",
            Race::Tauren => "tauren",
            Race::Troll => "troll",
            Race::Worgen => "worgen",
        };
        write!(f, "{}", s)
    }
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
