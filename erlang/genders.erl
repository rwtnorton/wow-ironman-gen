-module(genders).
-export([as_list/0, random/0, start/0, loop/0, rpc/2]).

as_list() ->
    [female, male].

random() ->
    sample:from_list(as_list()).

start() ->
    spawn(genders, loop, []).

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
