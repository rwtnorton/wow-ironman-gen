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
