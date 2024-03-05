import json
from dataclasses import dataclass
import os.path
from typing import Optional, Mapping

DEFAULT_PATH = os.path.join(os.path.dirname(__file__), 'data', 'pandaria.json')


@dataclass
class Loader:
    """
    Loads data dict from given JSON file.
    """

    path: Optional[str] = None
    data: Optional[Mapping] = None

    def __init__(self, path=DEFAULT_PATH):
        self.path = path
        self.data = {}
        with open(path, 'r') as fp:
            raw_data = json.load(fp)
        for race, attributes in raw_data.items():
            for key, value in attributes.items():
                if isinstance(value, list):
                    value = set(value)
                self.data.setdefault(race, {})[key] = value
