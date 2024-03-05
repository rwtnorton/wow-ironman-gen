from dataclasses import dataclass
import random
from typing import Optional, Any, Mapping


@dataclass
class Classes:
    """
    Class for extracting valid classes from data dict by race.
    """

    classes_by_race: Mapping[str, set[str]]

    def __init__(self, data: Mapping[str, Any]):
        self.classes_by_race = {}
        for race, attributes in data.items():
            classes = attributes['classes']
            self.classes_by_race[race] = classes

    def classes(self, race: str) -> set[str]:
        """
        Returns set of all valid classes for a given race.
        :param race: str
        :return: set of valid classes as strings
        """
        return self.classes_by_race.get(race, set())

    def random_class(self, race: str) -> Optional[str]:
        """
        Returns random wow class for a given race.
        :param race: str
        :return: wow class as string or None
        """
        cs = list(self.classes_by_race.get(race, set()))
        if cs:
            return random.choice(cs)
        return None
