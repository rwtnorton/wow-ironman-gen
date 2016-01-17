package main

import (
	"fmt"
	"math/rand"
	"time"
)

// # human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
// #   orc, troll, forsaken, tauren, blood_elf, goblin
// # # No death knights.
// # warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

type Race string

const (
	human     Race = "human"
	night_elf Race = "night_elf"
	dwarf     Race = "dwarf"
	gnome     Race = "gnome"
	draenei   Race = "draenei"
	worgen    Race = "worgen"
	pandaren  Race = "pandaren"
	orc       Race = "orc"
	troll     Race = "troll"
	forsaken  Race = "forsaken"
	tauren    Race = "tauren"
	blood_elf Race = "blood_elf"
	goblin    Race = "goblin"
)

var allRaces = []Race{
	human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
	orc, troll, forsaken, tauren, blood_elf, goblin,
}

func AllRaces() []Race { return allRaces }

type WowClass string

const (
	warrior WowClass = "warrior"
	paladin WowClass = "paladin"
	hunter  WowClass = "hunter"
	shaman  WowClass = "shaman"
	druid   WowClass = "druid"
	rogue   WowClass = "rogue"
	monk    WowClass = "monk"
	mage    WowClass = "mage"
	warlock WowClass = "warlock"
	priest  WowClass = "priest"
)

var allWowClasses = []WowClass{
	warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest,
}

func AllWowClasses() []WowClass { return allWowClasses }

type Gender string

const (
	female Gender = "female"
	male   Gender = "male"
)

var allGenders = []Gender{female, male}

func AllGenders() []Gender { return allGenders }

type Faction string

const (
	alliance Faction = "alliance"
	horde    Faction = "horde"
)

var allFactions = []Faction{horde, alliance}

func AllFactions() []Faction { return allFactions }

type Toon struct {
	race    Race
	cls     WowClass
	gender  Gender
	faction Faction
}

func (t *Toon) ShouldDisplayFaction() bool {
	return t.race == pandaren
}

func (t *Toon) String() string {
	if t.ShouldDisplayFaction() {
		return fmt.Sprintf("%s %s %s %s", t.gender, t.faction, t.race, t.cls)
	}
	return fmt.Sprintf("%s %s %s", t.gender, t.race, t.cls)
}

var WowClassesForRace = map[Race][]WowClass{
	human:     []WowClass{warrior, paladin, hunter, rogue, monk, mage, warlock, priest},
	night_elf: []WowClass{warrior, hunter, rogue, druid, monk, mage, priest},
	dwarf:     []WowClass{warrior, paladin, hunter, shaman, rogue, monk, mage, warlock, priest},
	gnome:     []WowClass{warrior, rogue, monk, mage, warlock, priest},
	draenei:   []WowClass{warrior, paladin, hunter, shaman, monk, mage, priest},
	worgen:    []WowClass{warrior, hunter, druid, rogue, mage, warlock, priest},
	pandaren:  []WowClass{warrior, hunter, shaman, rogue, monk, mage, priest},
	orc:       []WowClass{warrior, hunter, shaman, rogue, monk, mage, warlock},
	troll:     []WowClass{warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest},
	forsaken:  []WowClass{warrior, hunter, rogue, monk, mage, warlock, priest},
	tauren:    []WowClass{warrior, paladin, hunter, shaman, druid, monk, priest},
	blood_elf: []WowClass{warrior, paladin, hunter, rogue, monk, mage, warlock, priest},
	goblin:    []WowClass{warrior, hunter, shaman, rogue, mage, warlock, priest},
}

// type Whatever interface {}

// func Sample(lst []Whatever) Whatever {
// 	return lst[rand.Intn(len(lst))]
// }

func RandomRace(lst []Race) Race {
	return lst[rand.Intn(len(lst))]
}
func ChooseRace() Race {
	return RandomRace(allRaces)
}

func RandomWowClass(lst []WowClass) WowClass {
	return lst[rand.Intn(len(lst))]
}
func (r Race) ChooseWowClass() WowClass {
	return RandomWowClass(WowClassesForRace[r])
}

func RandomGender(lst []Gender) Gender {
	return lst[rand.Intn(len(lst))]
}
func ChooseGender() Gender {
	return RandomGender(allGenders)
}

func RandomFaction(lst []Faction) Faction {
	return lst[rand.Intn(len(lst))]
}
func ChooseFaction() Faction {
	return RandomFaction(allFactions)
}

func GenerateToon() *Toon {
	race := ChooseRace()
	cls := race.ChooseWowClass()
	gender := ChooseGender()
	faction := ChooseFaction()
	return &Toon{
		race:    race,
		cls:     cls,
		gender:  gender,
		faction: faction,
	}
}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())

	fmt.Println(GenerateToon())
}
