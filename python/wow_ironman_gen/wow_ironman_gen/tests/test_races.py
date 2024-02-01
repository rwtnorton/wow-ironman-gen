from wow_ironman_gen.races import Races


def test_races():
    data = dict(foo=1, bar=2, baz=3)
    r = Races(data)
    assert r is not None
    assert r.races == {'foo', 'bar', 'baz'}


def test_random_race():
    data = dict(foo=1, bar=2, baz=3)
    r = Races(data)
    assert r is not None
    got = r.random_race()
    assert got in data.keys()
    r = Races({})
    got = r.random_race()
    assert got is None
