(ns wow-ironman-gen.db)

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
