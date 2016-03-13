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

(defmulti sample count)
(defmethod sample 1 [coll]
  (first coll))
(defmethod sample :default [coll]
  (->> coll (into []) rand-nth))

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
  (->> by-race
       (map (partial *for-race :classes))
       (into {})))

(def factions-for-race
  (->> by-race
       (map (partial *for-race :factions))
       (into {})))

(defn gather-races []
  (keys classes-for-race))
(def races (gather-races))

(defn gather-classes []
  (->> races
       (map classes-for-race)
       (apply set/union)))
(def classes (gather-classes))

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

(defn- faction-count [toon]
  (->> toon :race factions-for-race count))

(defmulti toon->str faction-count)
(defmethod toon->str 2 [{:keys [race class gender faction]}]
  (str/join " " (map name [gender faction race class])))
(defmethod toon->str 1 [{:keys [race class gender]}]
  (str/join " " (map name [gender race class])))
(defmethod toon->str :default [toon]
  (throw (ex-info "Unexpected faction count"
                  {:toon toon
                   :faction-count (faction-count toon)})))

(->> (sample-toon)
     toon->str
     println)
