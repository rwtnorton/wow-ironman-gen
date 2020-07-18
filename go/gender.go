package main

import (
	"math/rand"
)

type Gender string

const (
	female Gender = "female"
	male   Gender = "male"
)

var allGenders = []Gender{female, male}

type genderChooser []Gender

func (gc genderChooser) Choose() interface{} {
	return gc[rand.Intn(len(gc))]
}
