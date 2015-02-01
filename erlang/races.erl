-module(races).
-export([as_list/0, random/0, start/0, loop/0, rpc/2]).

as_list() ->
    [
     human,
     night_elf,
     dwarf,
     gnome,
     draenei,
     worgen,
     pandaren,
     orc,
     troll,
     forsaken,
     tauren,
     blood_elf,
     goblin
    ].

random() ->
    sample:from_list(as_list()).

start() ->
    spawn(races, loop, []).

loop() ->
    receive
        {From, {random}} ->
            From ! {self(), random()},
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
