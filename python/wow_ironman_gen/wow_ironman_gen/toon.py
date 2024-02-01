from dataclasses import dataclass
from wow_ironman_gen.races import Races
from wow_ironman_gen.classes import Classes
from wow_ironman_gen.factions import Factions
from wow_ironman_gen.genders import Genders


@dataclass
class Toon:
    """
    A WoW Toon with race, class, faction, and gender.
    """

    race: str
    wow_class: str
    faction: str
    gender: str

    @classmethod
    def random_toon(cls, data: dict):
        """
        Generates a random Toon, from the given data.
        :param data: dictionary with race keys and
                     permitted attributes as values
        :return: Toon or raises a ValueError
        """
        r = Races(data).random_race()
        if r is None:
            raise ValueError(f"invalid races: data={data}")
        c = Classes(data).random_class(r)
        if c is None:
            raise ValueError(f"invalid class: race={r}, data={data}")
        f = Factions(data).random_faction(r)
        if f is None:
            raise ValueError(f"invalid faction: race={r}, data={data}")
        g = Genders(data).random_gender(r)
        if g is None:
            raise ValueError(f"invalid gender: race={r}, data={data}")
        return Toon(race=r, wow_class=c, faction=f, gender=g)

    def __str__(self) -> str:
        return ' '.join([
            self.gender,
            self.faction,
            self.race,
            self.wow_class])
