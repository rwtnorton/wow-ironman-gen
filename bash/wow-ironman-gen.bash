#!/bin/bash

##
## Randomly generate a World of Warcraft race/class/gender combo for the
## WoW Ironman Challenge.
##

# human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
#   orc, troll, forsaken, tauren, blood_elf, goblin
# # No death knights.
# warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

##
## Picks one among the argument list at random.
##
function samp {
    local as=("$@");
    local i=`expr $RANDOM % ${#as[*]}`;
    echo ${as[$i]};
}

##
## Choosing the classes for a given race relies on the convention
## of having a function with the same name as the race in the races
## array; said function returns an array of allowed classes.
## Not the greatest way to use indirection but it works.
##

races=(human night_elf dwarf gnome draenei worgen pandaren
       orc troll forsaken tauren blood_elf goblin);

human_cls=(warrior paladin hunter rogue monk mage warlock priest);
function human { echo "${human_cls[*]}"; }

night_elf_cls=(warrior hunter rogue druid monk mage priest);
function night_elf { echo "${night_elf_cls[*]}"; }

dwarf_cls=(warrior paladin hunter shaman rogue monk mage warlock priest);
function dwarf { echo "${dwarf_cls[*]}"; }

gnome_cls=(warrior rogue monk mage warlock priest);
function gnome { echo "${gnome_cls[*]}"; }

draenei_cls=(warrior paladin hunter shaman monk mage priest);
function draenei { echo "${draenei_cls[*]}"; }

worgen_cls=(warrior hunter druid rogue mage warlock priest);
function worgen { echo "${worgen_cls[*]}"; }

pandaren_cls=(warrior hunter shaman rogue monk mage priest);
function pandaren { echo "${pandaren_cls[*]}"; }

orc_cls=(warrior hunter shaman rogue monk mage warlock);
function orc { echo "${orc_cls[*]}"; }

troll_cls=(warrior hunter shaman druid rogue monk mage warlock priest);
function troll { echo "${troll_cls[*]}"; }

forsaken_cls=(warrior hunter rogue monk mage warlock priest);
function forsaken { echo "${forsaken_cls[*]}"; }

tauren_cls=(warrior paladin hunter shaman druid monk priest);
function tauren { echo "${tauren_cls[*]}"; }

blood_elf_cls=(warrior paladin hunter rogue monk mage warlock priest);
function blood_elf { echo "${blood_elf_cls[*]}"; }

goblin_cls=(warrior hunter shaman rogue mage warlock priest);
function goblin { echo "${goblin_cls[*]}"; }

race=`samp ${races[*]}`
race_cls=(`$race`);
cls=`samp ${race_cls[*]}`

genders=(male female)
gender=`samp ${genders[*]}`

factions=(alliance horde)
[ "$race" = 'pandaren' ] && faction=`samp ${factions[*]}`

result=($gender $faction $race $cls);
echo "${result[@]}"
