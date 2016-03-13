#!/usr/bin/env inlein
;; https://github.com/hyPiRion/inlein
;; `inlein --start-daemon` to speed up initial start time

'{:dependencies [[org.clojure/clojure "1.8.0"]]}

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

(def by-race
  {
   :human     {:factions #{:alliance}
               :classes  #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest}}
   :night_elf {:factions #{:alliance}
               :classes  #{:warrior :hunter :rogue :druid :monk :mage :priest}}
   :dwarf     {:factions #{:alliance}
               :classes  #{:warrior :paladin :hunter :shaman :rogue :monk :mage :warlock :priest}}
   :gnome     {:factions #{:alliance}
               :classes  #{:warrior :rogue :monk :mage :warlock :priest}}
   :draenei   {:factions #{:alliance}
               :classes  #{:warrior :paladin :hunter :shaman :monk :mage :priest}}
   :worgen    {:factions #{:alliance}
               :classes  #{:warrior :hunter :druid :rogue :mage :warlock :priest}}
   :pandaren  {:factions #{:alliance :horde}
               :classes  #{:warrior :hunter :shaman :rogue :monk :mage :priest}}
   :orc       {:factions #{:horde}
               :classes  #{:warrior :hunter :shaman :rogue :monk :mage :warlock}}
   :troll     {:factions #{:horde}
               :classes  #{:warrior :hunter :shaman :druid :rogue :monk :mage :warlock :priest}}
   :forsaken  {:factions #{:horde}
               :classes  #{:warrior :hunter :rogue :monk :mage :warlock :priest}}
   :tauren    {:factions #{:horde}
               :classes  #{:warrior :paladin :hunter :shaman :druid :monk :priest}}
   :blood_elf {:factions #{:horde}
               :classes  #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest}}
   :goblin    {:factions #{:horde}
               :classes  #{:warrior :hunter :shaman :rogue :mage :warlock :priest}}
   })

(def classes-for-race
  (into {} (map (fn [[r {vs :classes}]] [r vs]) (seq by-race))))

(def factions-for-race
  (into {} (map (fn [[r {vs :factions}]] [r vs]) (seq by-race))))

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

(println (present-toon (toon)))


