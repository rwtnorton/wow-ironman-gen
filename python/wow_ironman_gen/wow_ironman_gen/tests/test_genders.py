from wow_ironman_gen.genders import Genders


def test_genders():
    data = {
        'foo': {'genders': {1, 2}},
        'bar': {'genders': {3}}
    }
    g = Genders(data)
    assert g.genders('foo') == {1, 2}
    assert g.genders('bar') == {3}
    assert g.genders('quux') == set()


def test_random_gender():
    data = {
        'foo': {'genders': {1, 2}},
        'bar': {'genders': {3}}
    }
    g = Genders(data)
    got = g.random_gender('foo')
    assert got in data['foo']['genders']
    got = g.random_gender('quux')
    assert got is None
