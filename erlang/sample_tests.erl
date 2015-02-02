-module(sample_tests).
-include_lib("eunit/include/eunit.hrl").

from_list_test() ->
    [?_assertException(error, function_clause, sample:from_list([]))
    ,?_assert(sample:from_list([foo]) =:= foo)
    ,?_assert(lists:member(sample:from_list([0, 1, 2, 3]),
                           [0, 1, 2, 3]))].
