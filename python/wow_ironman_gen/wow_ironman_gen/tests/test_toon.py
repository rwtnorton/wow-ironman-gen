from wow_ironman_gen.toon import Toon


def test_toon_str():
    t = Toon(race='r', wow_class='c', gender='g', faction='f')
    assert str(t) == 'g f r c'


def test_toon_random_toon():
    data = {
        'foo': {
            'classes': ['c1', 'c2'],
            'factions': ['f1', 'f2'],
            'genders': ['g1', 'g2'],
        },
        'bar': {
            'classes': ['c3', 'c4'],
            'factions': ['f3', 'f4'],
            'genders': ['g3', 'g4'],
        },
    }
    t = Toon.random_toon(data)
    if t.race == 'foo':
        assert t.wow_class in data['foo']['classes']
        assert t.faction in data['foo']['factions']
        assert t.gender in data['foo']['genders']
    elif t.race == 'bar':
        assert t.wow_class in data['bar']['classes']
        assert t.faction in data['bar']['factions']
        assert t.gender in data['bar']['genders']
    else:
        assert False
