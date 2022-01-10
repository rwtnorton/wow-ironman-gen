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
use rand::distributions::{Distribution, Standard};
use rand::seq::SliceRandom;

fn main() {
    let mut rng = thread_rng();

    // let x: u32 = rng.gen();
    // println!("{}", x);
    // let toon = Toon { gender: Gender::Male, faction: Faction::Horde, race: Race::Orc, class: Class::Rogue };
    // println!("toon = {:?}", toon);
    // println!("gender = {}", toon.gender);
    // println!("faction = {}", toon.faction);
    // println!("race = {}", toon.race);
    // println!("class = {}", toon.class);
    // println!("toon = {}", toon);
    // println!("pandaren = {}", Toon { gender: Gender::Female, faction: Faction::Alliance, race: Race::Pandaren, class: Class::Monk });
    // println!("dwarf classes: {:?}", Race::Dwarf.allowed_classes());
    // println!("dwarf faction: {:?}", Race::Dwarf.allowed_faction());
    // println!("pandaren faction: {:?}", Race::Pandaren.allowed_faction());
    // let random_genders : Vec<Gender> = Standard.sample_iter(&mut rng).take(10).collect();
    // println!("{:?}", random_genders);
    // let random_factions : Vec<Faction> = Standard.sample_iter(&mut rng).take(10).collect();
    // println!("{:?}", random_factions);
    // let random_races : Vec<Race> = Standard.sample_iter(&mut rng).take(10).collect();
    // println!("{:?}", random_races);
    // let random_classes : Vec<Class> = Standard.sample_iter(&mut rng).take(10).collect();
    // println!("{:?}", random_classes);
    // for _i in 0..10 {
    //     println!("troll class: {}", Race::Troll.random_class(&mut rng));
    // }
    // for _i in 0..3 {
    //     println!("troll faction: {}", Race::Troll.random_faction(&mut rng));
    // }
    // for _i in 0..3 {
    //     println!("pandaren faction: {}", Race::Pandaren.random_faction(&mut rng));
    // }

    println!("{}", Toon::random_toon(&mut rng));
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

#[test]
fn test_gender_display() {
    assert_eq!(Gender::Female.to_string(), "female");
    assert_eq!(Gender::Male.to_string(), "male");
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

#[test]
fn test_faction_display() {
    assert_eq!(Faction::Alliance.to_string(), "alliance");
    assert_eq!(Faction::Horde.to_string(), "horde");
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Race {
    Human, NightElf, Dwarf, Gnome, Draenei, Worgen,
    Pandaren,
    Orc, Troll, Forsaken, Tauren, BloodElf, Goblin,
}

impl Race {
    const ALL : &'static [Race] = &[
        Race::BloodElf,
        Race::Draenei,
        Race::Dwarf,
        Race::Forsaken,
        Race::Gnome,
        Race::Goblin,
        Race::Human,
        Race::NightElf,
        Race::Orc,
        Race::Pandaren,
        Race::Tauren,
        Race::Troll,
        Race::Worgen,
    ];
}

impl Distribution<Race> for Standard {
    fn sample<R: Rng + ?Sized>(&self, rng: &mut R) -> Race {
        let i = rng.gen_range(0..Race::ALL.len());
        Race::ALL[i]
    }
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

#[test]
fn test_race_display() {
    assert_eq!(Race::BloodElf.to_string(), "blood_elf");
    assert_eq!(Race::Pandaren.to_string(), "pandaren");
    assert_eq!(Race::NightElf.to_string(), "night_elf");
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum Class {
    Warrior, Paladin, Hunter, Shaman, Druid,
    Rogue, Monk, Mage, Warlock, Priest,
}

impl Class {
    const ALL : &'static [Class] = &[
        Class::Druid,
        Class::Hunter,
        Class::Mage,
        Class::Monk,
        Class::Paladin,
        Class::Priest,
        Class::Rogue,
        Class::Shaman,
        Class::Warlock,
        Class::Warrior,
    ];

    const BLOOD_ELF_CLASSES: &'static [Class] = &[Class::Warrior, Class::Paladin, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const DRAENEI_CLASSES: &'static [Class] = &[Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Monk, Class::Mage, Class::Priest];
    const DWARF_CLASSES: &'static [Class] = &[Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const FORSAKEN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const GNOME_CLASSES: &'static [Class] = &[Class::Warrior, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const GOBLIN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Mage, Class::Warlock, Class::Priest];
    const HUMAN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Paladin, Class::Hunter, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const NIGHT_ELF_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Rogue, Class::Druid, Class::Monk, Class::Mage, Class::Priest];
    const ORC_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock];
    const PANDAREN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Shaman, Class::Rogue, Class::Monk, Class::Mage, Class::Priest];
    const TAUREN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Paladin, Class::Hunter, Class::Shaman, Class::Druid, Class::Monk, Class::Priest];
    const TROLL_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Shaman, Class::Druid, Class::Rogue, Class::Monk, Class::Mage, Class::Warlock, Class::Priest];
    const WORGEN_CLASSES: &'static [Class] = &[Class::Warrior, Class::Hunter, Class::Druid, Class::Rogue, Class::Mage, Class::Warlock, Class::Priest];
}

impl Distribution<Class> for Standard {
    fn sample<R: Rng + ?Sized>(&self, rng: &mut R) -> Class {
        let i = rng.gen_range(0..Class::ALL.len());
        Class::ALL[i]
    }
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
    fn allowed_classes(&self) -> &[Class] {
        match self {
            Race::BloodElf => Class::BLOOD_ELF_CLASSES,
            Race::Draenei => Class::DRAENEI_CLASSES,
            Race::Dwarf => Class::DWARF_CLASSES,
            Race::Forsaken => Class::FORSAKEN_CLASSES,
            Race::Gnome => Class::GNOME_CLASSES,
            Race::Goblin => Class::GOBLIN_CLASSES,
            Race::Human => Class::HUMAN_CLASSES,
            Race::NightElf => Class::NIGHT_ELF_CLASSES,
            Race::Orc => Class::ORC_CLASSES,
            Race::Pandaren => Class::PANDAREN_CLASSES,
            Race::Tauren => Class::TAUREN_CLASSES,
            Race::Troll => Class::TROLL_CLASSES,
            Race::Worgen => Class::WORGEN_CLASSES,
        }
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

    fn random_class<R: Rng + ?Sized>(&self, rng: &mut R) -> &Class {
        let classes = self.allowed_classes();
        match classes.choose(rng) {
            Some(class) => class,
            None => panic!("{} seems to have no allowed classes???", self)
        }
    }

    fn random_faction<R: Rng + ?Sized>(&self, rng: &mut R) -> Faction {
        match self.allowed_faction() {
            Some(faction) => faction,
            None => {
                match Faction::ALL.choose(rng) {
                    Some(faction) => *faction,
                    None => panic!("{} seems to have no allowed factions???", self)
                }
            }
        }
    }
}

impl Toon {
    fn random_toon<R: Rng + ?Sized>(rng: &mut R) -> Toon {
        let gender: Gender = rng.gen();
        let race: Race = rng.gen();
        let class = race.random_class(rng);
        let faction = race.random_faction(rng);
        Toon {
            gender: gender,
            race: race,
            class: *class,
            faction: faction,
        }
    }
}

#[test]
fn test_toon_display() {
    assert_eq!(Toon {
        gender: Gender::Female,
        faction: Faction::Alliance,
        race: Race::Gnome,
        class: Class::Warrior,
    }.to_string(), "female gnome warrior");
    assert_eq!(Toon {
        gender: Gender::Female,
        faction: Faction::Alliance,
        race: Race::Pandaren,
        class: Class::Rogue,
    }.to_string(), "female alliance pandaren rogue");
    assert_eq!(Toon {
        gender: Gender::Male,
        faction: Faction::Horde,
        race: Race::Pandaren,
        class: Class::Monk,
    }.to_string(), "male horde pandaren monk");
}
