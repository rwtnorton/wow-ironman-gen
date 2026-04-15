# `wow_ironman_gen`

SQLite 3 based system for generating toons suitable for the World Of Warcraft ironman challenge
(circa Pandaria).


## Usage

Basic use:
```
### Need to run `build` to populate wowironmangen.db file.
$ ./build
### All set to run queries against this SQLite3 db.
$ ./run
male alliance pandaren monk
```

To wipe all schema, rows, and the db file:
```
$ ./clean
```

To refresh the db with current schema and data (from `00-init.sql` file):
```
### Rebuilds wowironmangen.db from a clean slate.
$ ./reset
```

## Developing

Edit the file `00-init.sql` to your liking and then run `./reset`.
Thereafter, the `./run` script will generate toons based on your changes.
