#!/usr/bin/env bb
(ns wow-ironman-gen.main
 (:require [clojure.string :as str]))

(def data
 {:human     {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest}}
  :night_elf {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :rogue :druid :monk :mage :priest}}
  :dwarf     {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :paladin :hunter :shaman :rogue :monk :mage :warlock :priest}}
  :gnome     {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :rogue :monk :mage :warlock :priest}}
  :draenei   {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :paladin :hunter :shaman :monk :mage :priest}}
  :worgen    {:factions #{:alliance}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :druid :rogue :mage :warlock :priest}}
  :pandaren  {:factions #{:alliance :horde}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :shaman :rogue :monk :mage :priest}}
  :orc       {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :shaman :rogue :monk :mage :warlock}}
  :troll     {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :shaman :druid :rogue :monk :mage :warlock :priest}}
  :forsaken  {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :rogue :monk :mage :warlock :priest}}
  :tauren    {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :paladin :hunter :shaman :druid :monk :priest}}
  :blood_elf {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :paladin :hunter :rogue :monk :mage :warlock :priest}}
  :goblin    {:factions #{:horde}
              :genders  #{:female :male}
              :classes  #{:warrior :hunter :shaman :rogue :mage :warlock :priest}}})

(defrecord ByRaceData [by-race])

(defn new-db [data]
  (->ByRaceData data))

(defn get-races [db]
  (-> db :by-race keys))

(defn- race-data [db race]
  (if-let [m (-> db :by-race race)]
    m
    (throw (ex-info (format "unknown race: %s" race)
                    {:db db
                     :race race}))))

(defn get-classes-for-race [db race]
  (-> (race-data db race) :classes))

(defn get-factions-for-race [db race]
  (-> (race-data db race) :factions))

(defn get-genders-for-race [db race]
  (-> (race-data db race) :genders))


(defn random-race [db]
  (-> (get-races db) vec rand-nth))

(defn random-class [db race]
  (-> (get-classes-for-race db race) vec rand-nth))

(defn random-faction [db race]
  (-> (get-factions-for-race db race) vec rand-nth))

(defn random-gender [db race]
  (-> (get-genders-for-race db race) vec rand-nth))


(defrecord RandomToon [db race wow-class faction gender])

(defn new-random-toon [db]
  (let [race (random-race db)]
   (map->RandomToon {:db        db
                     :race      race
                     :wow-class (random-class db race)
                     :faction   (random-faction db race)
                     :gender    (random-gender db race)})))

(defn- pandaren?
  [{:keys [race] :as _toon}]
  (= race :pandaren))

(defn toon->str
  [{:keys [race wow-class faction gender]
    :as   toon}]
  (let [words (if (pandaren? toon)
                (map name [gender faction race wow-class])
                (map name [gender race wow-class]))]
    (str/join \space words)))

(let [db (new-db data)
      toon (new-random-toon db)]
 (println (toon->str toon)))
