(ns wow-ironman-gen.toon
  (:require [wow-ironman-gen.db :as db]
            [clojure.string :as str]))

(defrecord RandomToon [db race wow-class faction gender])

(defn new-random-toon [db]
  (let [race (db/random-race db)]
   (map->RandomToon {:db        db
                     :race      race
                     :wow-class (db/random-class db race)
                     :faction   (db/random-faction db race)
                     :gender    (db/random-gender db race)})))

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
