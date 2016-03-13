#!/usr/bin/env inlein
;; https://github.com/hyPiRion/inlein
;; `inlein --start-daemon` to speed up initial start time

'{:dependencies [[org.clojure/clojure "1.8.0"]]}

(require '[clojure.string :as str]
         '[clojure.set :as set])

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
  {:human     {:factions #{:alliance}
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
               :classes  #{:warrior :hunter :shaman :rogue :mage :warlock :priest}}})

(def genders #{:female :male})

(defn- *for-race [k [r m]]
  [r (get m k)])

(def classes-for-race
  (->> (seq by-race)
       (map (partial *for-race :classes))
       (into {})))

(def factions-for-race
  (->> (seq by-race)
       (map (partial *for-race :factions))
       (into {})))

(defn gather-classes []
  (->> races
       (map classes-for-race)
       (apply set/union)))
(def classes (gather-classes))

(defn gather-races []
  (keys classes-for-race))
(def races (gather-races))

(defn gather-factions []
  (->> races
       (map factions-for-race)
       (apply set/union)))
(def factions (gather-factions))

(defn sample-race []
  (->> races sample))

(defn sample-class [race]
  (->> race classes-for-race sample))

(defn sample-gender []
  (sample genders))

(defn sample-faction
  ([]
   (sample factions))
  ([race]
   (->> race factions-for-race sample)))

(defn sample-toon
  ([]
   (sample-toon (sample-race)))
  ([race]
   {:race race
    :class (sample-class race)
    :gender (sample-gender)
    :faction (sample-faction race)}))

(defmulti toon->str (fn [{race :race}]
                      (->> (factions-for-race race) count (> 1))))
(defmethod toon->str true [{:keys [race class gender faction]}]
  (str/join " " (map name [gender faction race class])))
(defmethod toon->str :default [{:keys [race class gender]}]
  (str/join " " (map name [gender race class])))

(->> (sample-toon)
     toon->str
     println)
