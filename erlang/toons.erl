-module(toons).
-import(sample, []).
-import(genders, []).
-import(factions, []).
-import(races, []).
-import(classes, []).
-export([random/0, random/1, start/0, loop/0, rpc/2]).

-record(toon, {gender, faction, race, class}).

random(Race) ->
    Faction = factions:random(Race),
    Gender = genders:random(),
    Class = classes:random(Race),
    #toon{gender=Gender
         ,faction=Faction
         ,race=Race
         ,class=Class}.
random() ->
    random(races:random()).

start() ->
    spawn(toons, loop, []).

loop() ->
    receive
        {From, {random}} ->
            From ! {self(), random()},
            loop();
        {From, {random, Race}} ->
            From ! {self(), random(Race)},
            loop()
    end.

rpc(Pid, Request) ->
    Pid ! {self(), Request},
    receive
        {Pid, Response} ->
            Response
    end.
