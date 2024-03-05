from wow_ironman_gen.loader import Loader
from os.path import join, dirname


def test_loader():
    loader = Loader(join(dirname(__file__), 'classic.json'))
    assert loader is not None
    assert loader.data == {
        'dwarf': {
            'classes': {'warrior', 'paladin', 'hunter', 'rogue', 'priest'},
            'factions': {'alliance'},
            'genders': {'female', 'male'},
        },
        'forsaken': {
            'classes': {'warrior', 'rogue', 'mage', 'warlock', 'priest'},
            'factions': {'horde'},
            'genders': {'female', 'male'},
        },
        'gnome': {
            'classes': {'warrior', 'rogue', 'mage', 'warlock', 'priest'},
            'factions': {'alliance'},
            'genders': {'female', 'male'},
        },
        'human': {
            'classes': {
                'warrior',
                'paladin',
                'rogue',
                'mage',
                'warlock',
                'priest',
            },
            'factions': {'alliance'},
            'genders': {'female', 'male'},
        },
        'night_elf': {
            'classes': {'warrior', 'hunter', 'rogue', 'druid', 'priest'},
            'factions': {'alliance'},
            'genders': {'female', 'male'},
        },
        'orc': {
            'classes': {'warrior', 'hunter', 'shaman', 'rogue', 'warlock'},
            'factions': {'horde'},
            'genders': {'female', 'male'},
        },
        'tauren': {
            'classes': {'warrior', 'hunter', 'shaman', 'druid'},
            'factions': {'horde'},
            'genders': {'female', 'male'},
        },
        'troll': {
            'classes': {
                'warrior',
                'hunter',
                'shaman',
                'rogue',
                'mage',
                'priest',
            },
            'factions': {'horde'},
            'genders': {'female', 'male'},
        },
    }
