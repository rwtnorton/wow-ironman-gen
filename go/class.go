package main

import (
	"math/rand"
)

type Class string

const (
	warrior Class = "warrior"
	paladin Class = "paladin"
	hunter  Class = "hunter"
	shaman  Class = "shaman"
	druid   Class = "druid"
	rogue   Class = "rogue"
	monk    Class = "monk"
	mage    Class = "mage"
	warlock Class = "warlock"
	priest  Class = "priest"
)

var allClasses = []Class{
	warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest,
}

func randomClass(lst []Class) Class {
	return lst[rand.Intn(len(lst))]
}
func (r Race) ChooseClass() Class {
	return randomClass(r.classes)
}
