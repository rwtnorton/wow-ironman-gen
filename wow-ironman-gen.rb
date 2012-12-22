#!/usr/bin/env ruby

##
## Randomly generate a World of Warcraft race/class/gender combo for the
## WoW Ironman Challenge.
##

# [:human, :night_elf, :dwarf, :gnome, :draenei, :worgen, :pandaren,
#  :orc, :troll, :forsaken, :tauren, :blood_elf, :goblin]
# # No death knights.
# [:warrior, :paladin, :hunter, :shaman, :druid, :rogue, :monk, :mage, :warlock, :priest]

classes_for_race = {
  human: [:warrior, :paladin, :hunter, :rogue, :monk, :mage, :warlock, :priest],
  night_elf: [:warrior, :hunter, :rogue, :druid, :monk, :mage, :priest],
  dwarf: [:warrior, :paladin, :hunter, :shaman, :rogue, :monk, :mage, :warlock,
          :priest],
  gnome: [:warrior, :rogue, :monk, :mage, :warlock, :priest],
  draenei: [:warrior, :paladin, :hunter, :shaman, :monk, :mage, :priest],
  worgen: [:warrior, :hunter, :druid, :rogue, :mage, :warlock, :priest],
  pandaren: [:warrior, :hunter, :shaman, :rogue, :monk, :mage, :priest],
  orc: [:warrior, :hunter, :shaman, :rogue, :monk, :mage, :warlock],
  troll: [:warrior, :hunter, :shaman, :druid, :rogue, :monk, :mage, :warlock,
          :priest],
  forsaken: [:warrior, :hunter, :rogue, :monk, :mage, :warlock, :priest],
  tauren: [:warrior, :paladin, :hunter, :shaman, :druid, :monk, :priest],
  blood_elf: [:warrior, :paladin, :hunter, :rogue, :monk, :mage, :warlock,
              :priest],
  goblin: [:warrior, :hunter, :shaman, :rogue, :mage, :warlock, :priest],
}

race = classes_for_race.keys.sample
cls = classes_for_race[race].sample
gender = [:male, :female].sample
faction = race == :pandaren ? [:alliance, :horde].sample : nil

result = [gender, faction, race, cls].select { |x| x }.join(' ')

puts result
