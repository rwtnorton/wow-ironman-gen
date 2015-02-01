-module(classes).
-import(sample, []).
-export([as_list/0, random/0, random/1, for_race/1, start/0, loop/0, rpc/2]).

as_list() ->
    [
     warrior,
     paladin,
     hunter,
     shaman,
     druid,
     rogue,
     monk,
     mage,
     warlock,
     priest
    ].

random() ->
    sample:from_list(as_list()).
random(Race) ->
    sample:from_list(for_race(Race)).

% Or a map would work too #{human => [...], ...}
for_race(human) ->
    [warrior, paladin, hunter, rogue, monk, mage, warlock, priest];
for_race(night_elf) ->
    [warrior, hunter, rogue, druid, monk, mage, priest];
for_race(dwarf) ->
    [warrior, paladin, hunter, shaman, rogue, monk, mage, warlock, priest];
for_race(gnome) ->
    [warrior, rogue, monk, mage, warlock, priest];
for_race(draenei) ->
    [warrior, paladin, hunter, shaman, monk, mage, priest];
for_race(worgen) ->
    [warrior, hunter, druid, rogue, mage, warlock, priest];
for_race(pandaren) ->
    [warrior, hunter, shaman, rogue, monk, mage, priest];
for_race(orc) ->
    [warrior, hunter, shaman, rogue, monk, mage, warlock];
for_race(troll) ->
    [warrior, hunter, shaman, druid, rogue, monk, mage, warlock, priest];
for_race(forsaken) ->
    [warrior, hunter, rogue, monk, mage, warlock, priest];
for_race(tauren) ->
    [warrior, paladin, hunter, shaman, druid, monk, priest];
for_race(blood_elf) ->
    [warrior, paladin, hunter, rogue, monk, mage, warlock, priest];
for_race(goblin) ->
    [warrior, hunter, shaman, rogue, mage, warlock, priest].

start() ->
    spawn(classes, loop, []).

loop() ->
    receive
        {From, {random}} ->
            From ! {self(), random()},
            loop();
        {From, {random, Race}} ->
            From ! {self(), random(Race)},
            loop();
        {From, {for_race, Race}} ->
            From ! {self(), for_race(Race)},
            loop();
        {From, {all}} ->
            From ! {self(), as_list()},
            loop()
    end.

rpc(Pid, Request) ->
    Pid ! {self(), Request},
    receive
        {Pid, Response} ->
            Response
    end.
