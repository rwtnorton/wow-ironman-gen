package main

import (
	"fmt"
)

type Toon struct {
	race    Race
	class   Class
	gender  Gender
	faction Faction
}

func (t *Toon) ShouldDisplayFaction() bool {
	return len(t.race.factions) > 1
}

func (t *Toon) String() string {
	if t.ShouldDisplayFaction() {
		return fmt.Sprintf("%s %s %s %s", t.gender, t.faction, t.race.String(), t.class)
	}
	return fmt.Sprintf("%s %s %s", t.gender, t.race.String(), t.class)
}

func RandomToon() *Toon {
	race := raceChooser(allRaces).Choose().(Race)
	class := race.ChooseClass()
	faction := factionChooser(race.factions).Choose().(Faction)
	gender := genderChooser(allGenders).Choose().(Gender)
	return &Toon{
		race:    race,
		class:   class,
		gender:  gender,
		faction: faction,
	}
}
