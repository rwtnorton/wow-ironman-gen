from wow_ironman_gen.factions import Factions


def test_factions():
    data = {'xyzzy': {'factions': {1, 2}}, 'quux': {'factions': {3}}}
    f = Factions(data)
    assert f.factions('xyzzy') == data['xyzzy']['factions']
    assert f.factions('quux') == data['quux']['factions']
    assert f.factions('floob') == set()


def test_random_faction():
    data = {'xyzzy': {'factions': {1, 2}}, 'quux': {'factions': {3}}}
    f = Factions(data)
    got = f.random_faction('xyzzy')
    assert got in data['xyzzy']['factions']
    got = f.random_faction('dunno')
    assert got is None
