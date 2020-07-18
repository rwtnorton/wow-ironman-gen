package main

import (
	"math/rand"
)

type Race struct {
	name     string
	classes  []Class
	factions []Faction
}

func (r *Race) String() string {
	return r.name
}

func (r *Race) HasMultipleFactions() bool {
	return len(r.factions) > 1
}

var (
	human = Race{
		name:     "human",
		classes:  []Class{warrior, paladin, hunter, rogue, monk, mage, warlock, priest},
		factions: allianceOnly,
	}
	nightElf = Race{
		name:     "night_elf",
		classes:  []Class{warrior, hunter, rogue, druid, monk, mage, priest},
		factions: allianceOnly,
	}
	dwarf = Race{
		name:     "dwarf",
		classes:  []Class{warrior, paladin, hunter, shaman, rogue, monk, mage, warlock, priest},
		factions: allianceOnly,
	}
	gnome = Race{
		name:     "gnome",
		classes:  []Class{warrior, rogue, monk, mage, warlock, priest},
		factions: allianceOnly,
	}
	draenei = Race{
		name:     "draenei",
		classes:  []Class{warrior, paladin, hunter, shaman, monk, mage, priest},
		factions: allianceOnly,
	}
	worgen = Race{
		name:     "worgen",
		classes:  []Class{warrior, hunter, druid, rogue, mage, warlock, priest},
		factions: allianceOnly,
	}
	pandaren = Race{
		name:     "pandaren",
		classes:  []Class{warrior, hunter, shaman, rogue, monk, mage, priest},
		factions: allFactions,
	}
	orc = Race{
		name:     "orc",
		classes:  []Class{warrior, hunter, shaman, rogue, monk, mage, warlock},
		factions: hordeOnly,
	}
	troll = Race{
		name:     "troll",
		classes:  []Class{warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest},
		factions: hordeOnly,
	}
	forsaken = Race{
		name:     "forsaken",
		classes:  []Class{warrior, hunter, rogue, monk, mage, warlock, priest},
		factions: hordeOnly,
	}
	tauren = Race{
		name:     "tauren",
		classes:  []Class{warrior, paladin, hunter, shaman, druid, monk, priest},
		factions: hordeOnly,
	}
	bloodElf = Race{
		name:     "blood_elf",
		classes:  []Class{warrior, paladin, hunter, rogue, monk, mage, warlock, priest},
		factions: hordeOnly,
	}
	goblin = Race{
		name:     "goblin",
		classes:  []Class{warrior, hunter, shaman, rogue, mage, warlock, priest},
		factions: hordeOnly,
	}
)

var allRaces = []Race{
	human, nightElf, dwarf, gnome, draenei, worgen,
	pandaren,
	orc, troll, forsaken, tauren, bloodElf, goblin,
}

type raceChooser []Race

func (rc raceChooser) Choose() interface{} {
	return rc[rand.Intn(len(rc))]
}
