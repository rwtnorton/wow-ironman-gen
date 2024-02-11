from dataclasses import dataclass
from typing import Optional, Any
import random


@dataclass
class Factions:
    """
    Class for extracting valid factions from data dict by race.
    """

    factions_for_race: dict[str, set[str]]

    def __init__(self, data: dict[str, Any]):
        self.factions_for_race = {}
        for race, attributes in data.items():
            self.factions_for_race[race] = set(attributes['factions'])

    def factions(self, race: str) -> set[str]:
        """
        Returns set of all valid factions for given race.
        :param race: str
        :return: set of faction strings
        """
        return self.factions_for_race.get(race, set())

    def random_faction(self, race: str) -> Optional[str]:
        """
        Returns random faction for given race.
        :param race: str
        :return: race str or None
        """
        fs = list(self.factions(race))
        if fs:
            return random.choice(fs)
        return None
