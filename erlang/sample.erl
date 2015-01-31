-module(sample).
-export([from_list/1]).

from_list([Head | Rest]) ->
    Vs = [Head | Rest],
    Index = random:uniform(length(Vs)),
    lists:nth(Index, Vs).
