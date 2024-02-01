# wow_ironman_gen

Python 3 library for generating toons suitable for the World Of Warcraft ironman challenge
(circa Pandaria).


## Usage

Basic use:
```
$ ./main_cli.py
male alliance pandaren monk
```

See options:
```
$ ./main_cli.py --help
usage: wow_ironman_gen [-h] [--json_file JSON_FILE]

Generate a random Toon suitable for WoW Ironman

options:
  -h, --help            show this help message and exit
  --json_file JSON_FILE
```

Run with Classic race/class combinations:
```
$ ./main_cli.py --json_file wow_ironman_gen/data/classic.json
female horde orc shaman
```

## Tests

```
$ ./run_tests
```

## Linting

Uses `flake8`.
```
$ ./lint
```
