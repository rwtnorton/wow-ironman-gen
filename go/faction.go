package main

import (
	"math/rand"
)

type Faction string

const (
	alliance Faction = "alliance"
	horde    Faction = "horde"
)

type factionChooser []Faction

var (
	allFactions  factionChooser = []Faction{horde, alliance}
	allianceOnly factionChooser = []Faction{alliance}
	hordeOnly    factionChooser = []Faction{horde}
)

func (fc factionChooser) Choose() interface{} {
	return fc[rand.Intn(len(fc))]
}
