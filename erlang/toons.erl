-module(toons).
-import(sample, []).
-import(genders, []).
-import(factions, []).
-import(races, []).
-import(classes, []).
-export([random/0, random/1, to_str/1, start/0, loop/0, rpc/2]).

random(Race) ->
    Faction = factions:random(Race),
    Gender = genders:random(),
    Class = classes:random(Race),
    #{gender  => Gender
     ,faction => Faction
     ,race    => Race
     ,class   => Class}.
random() ->
    random(races:random()).

raw_to_str(Fmt, Args) ->
    lists:flatten(io_lib:format(Fmt, Args)).

to_str(#{class := Class
        ,faction := Faction
        ,gender := Gender
        ,race := pandaren}) ->
    raw_to_str("~p ~p ~p ~p", [Gender, Faction, pandaren, Class]);
to_str(#{class := Class
        ,faction := _
        ,gender := Gender
        ,race := Race}) ->
    raw_to_str("~p ~p ~p", [Gender, Race, Class]).

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
