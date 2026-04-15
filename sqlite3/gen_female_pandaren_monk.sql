SELECT
   g.gender_name
 , f.faction_name
 , r.race_name
 , c.class_name
FROM races r
   , classes c
   , factions f
   , genders g
INNER JOIN race_class rc   ON (rc.race_id=r.id AND rc.class_id=c.id)
INNER JOIN race_faction rf ON (rf.race_id=r.id AND rf.faction_id=f.id)
INNER JOIN race_gender rg  ON (rg.race_id=r.id AND rg.gender_id=g.id)
-- WHERE f.faction_name = 'alliance'
WHERE r.race_name = 'pandaren' AND g.gender_name = 'female' AND c.class_name = 'monk'
ORDER BY g.gender_name, f.faction_name, r.race_name, c.class_name
;
