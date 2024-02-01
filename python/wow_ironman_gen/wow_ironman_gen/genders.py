from dataclasses import dataclass
from typing import Optional
import random


@dataclass
class Genders:
    """
    Class to extract valid genders from data dict by race.
    """

    genders_for_race: dict

    def __init__(self, data: dict):
        self.genders_for_race = {}
        for race, attributes in data.items():
            self.genders_for_race[race] = set(attributes['genders'])

    def genders(self, race: str) -> set[str]:
        """
        Returns valid genders for given race.
        :param race: str
        :return: set of valid genders
        """
        return self.genders_for_race.get(race, set())

    def random_gender(self, race: str) -> Optional[str]:
        """
        Returns a random gender for given race.
        :param race: str
        :return: gender str or None
        """
        gs = list(self.genders(race))
        if gs:
            return random.choice(gs)
        return None
