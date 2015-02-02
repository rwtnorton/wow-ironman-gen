-module(genders_tests).
-include_lib("eunit/include/eunit.hrl").

as_list_test() ->
    Expect = lists:sort([female, male]),
    Expect = lists:sort(genders:as_list()).

random_test() ->
    Got = genders:random(),
    All = genders:as_list(),
    true = lists:member(Got, All).
