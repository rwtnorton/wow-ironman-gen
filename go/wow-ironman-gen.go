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

type Chooser interface {
	Choose() interface{}
}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())

	fmt.Println(RandomToon())
}
