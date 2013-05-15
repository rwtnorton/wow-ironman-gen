#!/usr/bin/env groovy

//
// Randomly generate a World of Warcraft race/class/gender combo for the
// WoW Ironman Challenge.
//

// [:human, :night_elf, :dwarf, :gnome, :draenei, :worgen, :pandaren,
//  :orc, :troll, :forsaken, :tauren, :blood_elf, :goblin]
// # No death knights.
// [:warrior, :paladin, :hunter, :shaman, :druid, :rogue, :monk,
//    :mage, :warlock, :priest]

def sample(lst) {
  Random r = new Random()
  int choice = r.nextInt(lst.size())
  lst[choice]
}

final HashMap<String, ArrayList<String>> CLASSES_FOR_RACE = [
  "human": ["warrior", "paladin", "hunter", "rogue", "monk", "mage",
            "warlock", "priest"],
  "night_elf": ["warrior", "hunter", "rogue", "druid", "monk", "mage",
                "priest"],
  "dwarf": ["warrior", "paladin", "hunter", "shaman", "rogue", "monk",
            "mage", "warlock", "priest"],
  "gnome": ["warrior", "rogue", "monk", "mage", "warlock", "priest"],
  "draenei": ["warrior", "paladin", "hunter", "shaman", "monk", "mage",
              "priest"],
  "worgen": ["warrior", "hunter", "druid", "rogue", "mage", "warlock",
             "priest"],
  "pandaren": ["warrior", "hunter", "shaman", "rogue", "monk", "mage",
               "priest"],
  "orc": ["warrior", "hunter", "shaman", "rogue", "monk", "mage", "warlock"],
  "troll": ["warrior", "hunter", "shaman", "druid", "rogue", "monk", "mage",
            "warlock", "priest"],
  "forsaken": ["warrior", "hunter", "rogue", "monk", "mage", "warlock",
               "priest"],
  "tauren": ["warrior", "paladin", "hunter", "shaman", "druid", "monk",
             "priest"],
  "blood_elf": ["warrior", "paladin", "hunter", "rogue", "monk", "mage",
                "warlock", "priest"],
  "goblin": ["warrior", "hunter", "shaman", "rogue", "mage", "warlock",
             "priest"]
]
//println classes_for_race

final ArrayList<String> GENDERS = ["male", "female"]
//println genders

final ArrayList<String> FACTIONS = ["alliance", "horde"]
//println factions

final ArrayList<String> RACES = CLASSES_FOR_RACE.keySet()
//println races

/*
 * Not sure that this use of closures is idiomatic at all, but
 * it was what I found that kept genders/races/factions/classes...
 * within scope.  I imagine that defining a class might be more
 * idiomatic Groovy.
 */
def randomRace = { sample(RACES) }
def randomClassForRace = { race -> sample(CLASSES_FOR_RACE[race]) }
def randomGender = { sample(GENDERS) }
def randomFaction = { sample(FACTIONS) }

String race = randomRace()
String cls = randomClassForRace(race)
String gender = randomGender()
String faction = race == "pandaren" ? randomFaction() : null

String result = [gender, faction, race, cls].findAll { it != null }.join(' ')

println result
