CREATE TABLE races (
    id INTEGER PRIMARY KEY,
    race_name TEXT NOT NULL UNIQUE
);

CREATE TABLE classes (
    id INTEGER PRIMARY KEY,
    class_name TEXT NOT NULL UNIQUE
);

CREATE TABLE factions (
    id INTEGER PRIMARY KEY,
    faction_name TEXT NOT NULL UNIQUE
);

CREATE TABLE genders (
    id INTEGER PRIMARY KEY,
    gender_name TEXT NOT NULL UNIQUE
);

INSERT INTO genders (gender_name)
VALUES ('female')
     , ('male')
;

INSERT INTO factions (faction_name)
VALUES ('alliance')
     , ('horde')
;

INSERT INTO races (race_name)
VALUES ('human')
     , ('night_elf')
     , ('dwarf')
     , ('gnome')
     , ('draenei')
     , ('worgen')
     , ('pandaren')
     , ('orc')
     , ('troll')
     , ('forsaken')
     , ('tauren')
     , ('blood_elf')
     , ('goblin')
;

INSERT INTO classes (class_name)
VALUES ('warrior')
     , ('paladin')
     , ('hunter')
     , ('shaman')
     , ('rogue')
     , ('druid')
     , ('monk')
     , ('mage')
     , ('warlock')
     , ('priest')
;

CREATE TABLE race_class (
    id INTEGER PRIMARY KEY,
    race_id INTEGER REFERENCES races(id),
    class_id INTEGER REFERENCES classes(id)
);

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'human'
  AND c.class_name IN (
    'warrior', 'paladin', 'hunter', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'night_elf'
  AND c.class_name IN (
    'warrior', 'hunter', 'rogue', 'druid', 'monk', 'mage', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'dwarf'
  AND c.class_name IN (
    'warrior', 'paladin', 'hunter', 'shaman', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'gnome'
  AND c.class_name IN (
    'warrior', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'draenei'
  AND c.class_name IN (
    'warrior', 'paladin', 'hunter', 'shaman', 'monk', 'mage', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'worgen'
  AND c.class_name IN (
    'warrior', 'hunter', 'druid', 'rogue', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'pandaren'
  AND c.class_name IN (
    'warrior', 'hunter', 'shaman', 'rogue', 'monk', 'mage', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'orc'
  AND c.class_name IN (
    'warrior', 'hunter', 'shaman', 'rogue', 'monk', 'mage', 'warlock')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'troll'
  AND c.class_name IN (
    'warrior', 'hunter', 'shaman', 'druid', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'forsaken'
  AND c.class_name IN (
    'warrior', 'hunter', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'tauren'
  AND c.class_name IN (
    'warrior', 'paladin', 'hunter', 'shaman', 'druid', 'monk', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'blood_elf'
  AND c.class_name IN (
    'warrior', 'paladin', 'hunter', 'rogue', 'monk', 'mage', 'warlock', 'priest')
;

INSERT INTO race_class (race_id, class_id)
SELECT r.id AS "race_id", c.id AS "class_id"
FROM races r CROSS JOIN classes c
WHERE r.race_name = 'goblin'
  AND c.class_name IN (
    'warrior', 'hunter', 'shaman', 'rogue', 'mage', 'warlock', 'priest')
;

CREATE TABLE race_gender (
    id INTEGER PRIMARY KEY,
    race_id INTEGER REFERENCES races(id),
    gender_id INTEGER REFERENCES genders(id)
);

-- Full Cartesian product.
INSERT INTO race_gender (race_id, gender_id)
SELECT r.id AS "race_id", g.id AS "gender_id"
FROM races r CROSS JOIN genders g
;

CREATE TABLE race_faction (
    id INTEGER PRIMARY KEY,
    race_id INTEGER REFERENCES races(id),
    faction_id INTEGER REFERENCES factions(id)
);

INSERT INTO race_faction (race_id, faction_id)
SELECT r.id AS "race_id", f.id AS "faction_id"
FROM races r CROSS JOIN factions f
WHERE r.race_name IN ('human', 'night_elf', 'dwarf', 'gnome', 'draenei', 'worgen', 'pandaren')
  AND f.faction_name = 'alliance'
;

INSERT INTO race_faction (race_id, faction_id)
SELECT r.id AS "race_id", f.id AS "faction_id"
FROM races r CROSS JOIN factions f
WHERE r.race_name IN ('orc', 'troll', 'forsaken', 'tauren', 'blood_elf', 'goblin', 'pandaren')
  AND f.faction_name = 'horde'
;
