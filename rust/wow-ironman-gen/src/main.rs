//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

use std::fmt;
use std::collections::HashSet;
use rand::{thread_rng, Rng};
use rand::distributions::{Distribution, Standard};

fn main() {
    let mut rng = thread_rng();
    let x: u32 = rng.gen();
    println!("{}", x);
    let toon = Toon { gender: Gender::Male, faction: Faction::Horde, race: Race::Orc, class: Class::Rogue };
    println!("toon = {:?}", toon);
    println!("gender = {}", toon.gender);
    println!("faction = {}", toon.faction);
    println!("race = {}", toon.race);
    println!("class = {}", toon.class);
    println!("toon = {}", toon);
    println!("pandaren = {}", Toon { gender: Gender::Female, faction: Faction::Alliance, race: Race::Pandaren, class: Class::Monk });
    println!("dwarf classes: {:?}", Race::Dwarf.allowed_classes());
    println!("dwarf faction: {:?}", Race::Dwarf.allowed_faction());
    println!("pandaren faction: {:?}", Race::Pandaren.allowed_faction());
    let random_genders : Vec<Gender> = Standard.sample_iter(&mut rng).take(10).collect();
    println!("{:?}", random_genders);
    let random_factions : Vec<Faction> = Standard.sample_iter(&mut rng).take(10).collect();
    println!("{:?}", random_factions);
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Gender { Female, Male }

impl Gender {
    const ALL : &'static [Gender] = &[Gender::Female, Gender::Male];
}

impl Distribution<Gender> for Standard {
    fn sample<R: Rng + ?Sized>(&self, rng: &mut R) -> Gender {
        let i = rng.gen_range(0..Gender::ALL.len());
        Gender::ALL[i]
    }
}

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

impl Faction {
    const ALL : &'static [Faction] = &[Faction::Alliance, Faction::Horde];
}

impl Distribution<Faction> for Standard {
    fn sample<R: Rng + ?Sized>(&self, rng: &mut R) -> Faction {
        let i = rng.gen_range(0..Faction::ALL.len());
        Faction::ALL[i]
    }
}

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

impl fmt::Display for Class {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        let s = match self {
            Class::Druid => "druid",
            Class::Hunter => "hunter",
            Class::Mage => "mage",
            Class::Monk => "monk",
            Class::Paladin => "paladin",
            Class::Priest => "priest",
            Class::Rogue => "rogue",
            Class::Shaman => "shaman",
            Class::Warlock => "warlock",
            Class::Warrior => "warrior",
        };
        write!(f, "{}", s)
    }
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
struct Toon {
    race: Race,
    class: Class,
    gender: Gender,
    faction: Faction,
}

impl fmt::Display for Toon {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        let s = match self.race {
            Race::Pandaren => format!("{} {} {} {}", self.gender, self.faction, self.race, self.class),
            _ => format!("{} {} {}", self.gender, self.race, self.class),
        };
        write!(f, "{}", s)
    }
}

impl Race {
    fn allowed_classes(&self) -> HashSet<Class> {
        match self {
            Race::BloodElf => vec![Class::Warrior, Class::Paladin, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::Draenei => vec![Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Monk, Class::Mage, Class::Priest],
            Race::Dwarf => vec![Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::Forsaken => vec![Class::Warrior, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::Gnome => vec![Class::Warrior, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::Goblin => vec![Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Mage, Class::Warlock, Class::Priest],
            Race::Human => vec![Class::Warrior, Class::Paladin, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::NightElf => vec![Class::Warrior, Class::Hunter, Class::Rogue, Class::Druid, Class::Monk, Class::Mage, Class::Priest],
            Race::Orc => vec![Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock],
            Race::Pandaren => vec![Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Priest],
            Race::Tauren => vec![Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Druid, Class::Monk, Class::Priest],
            Race::Troll => vec![Class::Warrior, Class::Hunter, Class::Shaman, Class::Druid, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest],
            Race::Worgen => vec![Class::Warrior, Class::Hunter, Class::Druid, Class::Rogue, Class::Mage, Class::Warlock, Class::Priest],
        }.into_iter().collect()
    }

    fn allowed_faction(&self) -> Option<Faction> {
        match self {
            Race::BloodElf => Some(Faction::Horde),
            Race::Draenei => Some(Faction::Alliance),
            Race::Dwarf => Some(Faction::Alliance),
            Race::Forsaken => Some(Faction::Horde),
            Race::Gnome => Some(Faction::Alliance),
            Race::Goblin => Some(Faction::Horde),
            Race::Human => Some(Faction::Alliance),
            Race::NightElf => Some(Faction::Alliance),
            Race::Orc => Some(Faction::Horde),
            Race::Pandaren => None,
            Race::Tauren => Some(Faction::Horde),
            Race::Troll => Some(Faction::Horde),
            Race::Worgen => Some(Faction::Alliance),
        }
    }
}
