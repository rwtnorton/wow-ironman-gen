(ns clj-wow-ironman-gen.core
  (:gen-class))

;;
;; Randomly generate a World of Warcraft race/class/gender combo for the
;; WoW Ironman Challenge.
;;

;; human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
;;  orc, troll, forsaken, tauren, blood_elf, goblin
;; No death knights.
;; warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

(defn sample [coll]
  (rand-nth (seq coll)))

(def classes-for-race
  {
   :human     #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest},
   :night_elf #{:warrior :hunter :rogue :druid :monk :mage :priest},
   :dwarf     #{:warrior :paladin :hunter :shaman :rogue :monk :mage :warlock
                :priest},
   :gnome     #{:warrior :rogue :monk :mage :warlock :priest}
   :draenei   #{:warrior :paladin :hunter :shaman :monk :mage :priest},
   :worgen    #{:warrior :hunter :druid :rogue :mage :warlock :priest},
   :pandaren  #{:warrior :hunter :shaman :rogue :monk :mage :priest},
   :orc       #{:warrior :hunter :shaman :rogue :monk :mage :warlock},
   :troll     #{:warrior :hunter :shaman :druid :rogue :monk :mage :warlock
                :priest},
   :forsaken  #{:warrior :hunter :rogue :monk :mage :warlock :priest},
   :tauren    #{:warrior :paladin :hunter :shaman :druid :monk :priest},
   :blood_elf #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest},
   :goblin    #{:warrior :hunter :shaman :rogue :mage :warlock :priest},
   })

(def factions-for-race
  {
   :human     #{:alliance},
   :night_elf #{:alliance},
   :dwarf     #{:alliance},
   :gnome     #{:alliance},
   :draenei   #{:alliance},
   :worgen    #{:alliance},
   :pandaren  #{:alliance :horde},
   :orc       #{:horde},
   :troll     #{:horde},
   :forsaken  #{:horde},
   :tauren    #{:horde},
   :blood_elf #{:horde},
   :goblin    #{:horde},
   })

(defn races [] (keys classes-for-race))

(defn race [] (sample (races)))

(defn cls [race] (sample (classes-for-race race)))

(defn gender [] (sample #{:female :male}))

(defn faction
  ([] (sample #{:horde :alliance}))
  ([r] (sample (factions-for-race r))))

(defn toon []
  (let [r (race)
        c (cls r)
        g (gender)
        f (faction r)]
    {:race r, :cls c, :gender g, :faction f}))

(defn present-toon [toon]
  (let [{r :race, c :cls, g :gender, f :faction} toon
        attrs (filter identity [g (when (= r :pandaren) f) r c])]
    (clojure.string/join " " (map name attrs))))

(defn -main [& args]
  (println (present-toon (toon))))
