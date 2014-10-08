#!/usr/bin/env python

import random
import string

##
## Randomly generate a World of Warcraft race/class/gender combo for the
## WoW Ironman Challenge.
##

# human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
#   orc, troll, forsaken, tauren, blood_elf, goblin
# # No death knights.
# warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

def sample(lst):
    """sample(lst) -> Random member of list lst.

    Really just present for convenience.
    """
    return random.sample(lst, 1)[0]

CLASSES_FOR_RACE = {
    "human": ("warrior", "paladin", "hunter", "rogue", "monk", "mage",
              "warlock", "priest"),
    "night_elf": ("warrior", "hunter", "rogue", "druid", "monk", "mage",
                  "priest"),
    "dwarf": ("warrior", "paladin", "hunter", "shaman", "rogue", "monk",
              "mage", "warlock", "priest"),
    "gnome": ("warrior", "rogue", "monk", "mage", "warlock", "priest"),
    "draenei": ("warrior", "paladin", "hunter", "shaman", "monk", "mage",
                "priest"),
    "worgen": ("warrior", "hunter", "druid", "rogue", "mage", "warlock",
               "priest"),
    "pandaren": ("warrior", "hunter", "shaman", "rogue", "monk", "mage",
                 "priest"),
    "orc": ("warrior", "hunter", "shaman", "rogue", "monk", "mage", "warlock"),
    "troll": ("warrior", "hunter", "shaman", "druid", "rogue", "monk", "mage",
              "warlock", "priest"),
    "forsaken": ("warrior", "hunter", "rogue", "monk", "mage", "warlock",
                 "priest"),
    "tauren": ("warrior", "paladin", "hunter", "shaman", "druid", "monk",
               "priest"),
    "blood_elf": ("warrior", "paladin", "hunter", "rogue", "monk", "mage",
                  "warlock", "priest"),
    "goblin": ("warrior", "hunter", "shaman", "rogue", "mage", "warlock",
               "priest")
}

def races():
    return CLASSES_FOR_RACE.keys()

def genders():
    return ("male", "female")

def factions():
    return ("alliance", "horde")

def random_race():
    return sample(races())

def random_class_for_race(race):
    return sample(CLASSES_FOR_RACE[race])

def random_gender():
    return sample(genders())

def random_faction():
    return sample(factions())


class toon(object):
    def __init__(self, race, cls, gender, faction):
        self.race = race
        self.cls = cls
        self.gender = gender
        self.faction = faction

    def is_multi_faction(self):
        return self.race == "pandaren"

    def attributes_for_display(self):
        result = [self.gender]
        if self.is_multi_faction():
            result.append(self.faction)
        result.extend([self.race, self.cls])
        return result

    def __repr__(self):
        return string.join([str(x) for x in self.attributes_for_display()])


def random_toon():
    r = random_race()
    c = random_class_for_race(r)
    g = random_gender()
    f = random_faction()
    return toon(race=r, cls=c, gender=g, faction=f)

if __name__ == '__main__':
    print random_toon()
