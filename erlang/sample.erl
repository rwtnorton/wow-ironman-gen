-module(sample).
-export([from_list/1]).

from_list(Vs = [_Head | _Rest]) ->
    Index = random:uniform(length(Vs)),
    lists:nth(Index, Vs).
