-module(factions).
-export([as_list/0, random/0, random/1, for_race/1, start/0, loop/0, rpc/2]).

as_list() ->
    [horde, alliance].

random() ->
    sample:from_list(as_list()).
random(Race) ->
    sample:from_list(for_race(Race)).

for_race(human)     -> [alliance];
for_race(night_elf) -> [alliance];
for_race(dwarf)     -> [alliance];
for_race(gnome)     -> [alliance];
for_race(draenei)   -> [alliance];
for_race(worgen)    -> [alliance];
for_race(pandaren)  -> as_list();
for_race(orc)       -> [horde];
for_race(troll)     -> [horde];
for_race(forsaken)  -> [horde];
for_race(tauren)    -> [horde];
for_race(blood_elf) -> [horde];
for_race(goblin)    -> [horde].

start() ->
    spawn(factions, loop, []).

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
