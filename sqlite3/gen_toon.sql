SELECT
 CONCAT_WS(' ',
   g.gender_name
 , CASE r.race_name
   WHEN 'pandaren' THEN f.faction_name
   ELSE NULL
   END
 , r.race_name
 , c.class_name
)
FROM races r
   , classes c
   , factions f
   , genders g
INNER JOIN race_class rc   ON (rc.race_id=r.id AND rc.class_id=c.id)
INNER JOIN race_faction rf ON (rf.race_id=r.id AND rf.faction_id=f.id)
INNER JOIN race_gender rg  ON (rg.race_id=r.id AND rg.gender_id=g.id)
ORDER BY RANDOM()
LIMIT 1
;
