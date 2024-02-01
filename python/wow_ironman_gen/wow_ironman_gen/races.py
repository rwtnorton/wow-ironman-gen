from dataclasses import dataclass
from typing import Optional
import random


@dataclass
class Races:
    """
    Class for extracting valid races from data dict.
    """

    races: set[str]

    def __init__(self, data: dict):
        self.races = set(data.keys())

    def random_race(self) -> Optional[str]:
        """
        Returns a random race.
        :return: str race or None if no race is available in data
        """
        rs = list(self.races)
        if rs:
            return random.choice(rs)
        return None
