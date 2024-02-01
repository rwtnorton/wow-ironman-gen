from wow_ironman_gen.classes import Classes


def test_classes():
    data = {
        'florb': {'classes': {1, 2, 3}},
        'glarch': {'classes': {4, 5}}
    }
    c = Classes(data)
    assert c.classes('florb') == {1, 2, 3}
    assert c.classes('glarch') == {4, 5}
    assert c.classes('lala') == set()


def test_random_class():
    data = {
        'florb': {'classes': {1, 2, 3}},
        'glarch': {'classes': {4, 5}}
    }
    c = Classes(data)
    got = c.random_class('florb')
    assert got in data['florb']['classes']
    assert c.random_class('wackawacka') is None
