#!/usr/bin/env node

/*
 * Randomly generate a World of Warcraft race/class/gender combo for the
 * WoW Ironman Challenge.
 */

// human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
//   orc, troll, forsaken, tauren, blood_elf, goblin
// No death knights.
// warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

function randomInt(modulus) {
    return Math.floor(Math.random() * modulus);
}
Array.prototype.sample = function() {
    return this[ randomInt(this.length) ];
};
function buildSampler(choices) {
    return function() { return choices.sample(); };
}

var classesForRace = {
    human: ['warrior', 'paladin', 'hunter', 'rogue', 'monk', 'mage', 'warlock',
            'priest'],
    night_elf: ['warrior', 'hunter', 'rogue', 'druid', 'monk', 'mage',
                'priest'],
    dwarf: ['warrior', 'paladin', 'hunter', 'shaman', 'rogue', 'monk', 'mage',
            'warlock', 'priest'],
    gnome: ['warrior', 'rogue', 'monk', 'mage', 'warlock', 'priest'],
    draenei: ['warrior', 'paladin', 'hunter', 'shaman', 'monk', 'mage',
              'priest'],
    worgen: ['warrior', 'hunter', 'druid', 'rogue', 'mage', 'warlock',
             'priest'],
    pandaren: ['warrior', 'hunter', 'shaman', 'rogue', 'monk', 'mage',
               'priest'],
    orc: ['warrior', 'hunter', 'shaman', 'rogue', 'monk', 'mage', 'warlock'],
    troll: ['warrior', 'hunter', 'shaman', 'druid', 'rogue', 'monk', 'mage',
            'warlock', 'priest'],
    forsaken: ['warrior', 'hunter', 'rogue', 'monk', 'mage', 'warlock',
               'priest'],
    tauren: ['warrior', 'paladin', 'hunter', 'shaman', 'druid', 'monk',
             'priest'],
    blood_elf: ['warrior', 'paladin', 'hunter', 'rogue', 'monk', 'mage',
                'warlock', 'priest'],
    goblin: ['warrior', 'hunter', 'shaman', 'rogue', 'mage', 'warlock',
             'priest'],
};
exports.classesForRace = function() { return classesForRace; };
var races = exports.races = function() {
    var result = [];
    for (var k in classesForRace) {
        result.push(k);
    }
    return result;
};

var chooseRace = exports.chooseRace = buildSampler(races());
var chooseClass = exports.chooseClass = function(race) {
    return classesForRace[race].sample();
};
var chooseGender = exports.chooseGender = buildSampler(['female', 'male']);
var chooseFaction = exports.chooseFaction = buildSampler(['horde', 'alliance']);
var buildToon = exports.buildToon = function() {
    var race = chooseRace(),
        cls = chooseClass(race),
        gender = chooseGender(),
        faction = chooseFaction();
    return { race: race, cls: cls, gender: gender, faction: faction };
};
var presentToon = exports.presentToon = function(toon) {
    var attrs = (toon.race == 'pandaren') ?
        [toon.gender, toon.faction, toon.race, toon.cls] :
        [toon.gender,               toon.race, toon.cls];
    return attrs.join(' ');
};
var main = exports.main = function() {
    console.log(presentToon(buildToon()));
};
main();
