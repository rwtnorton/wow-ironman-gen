(ns wow-ironman-gen.main
  (:require [wow-ironman-gen.loader :as loader]
            [wow-ironman-gen.db :as db]
            [wow-ironman-gen.toon :as toon]))

(defn -main
  [& _args]
  (let [data (loader/load-edn)
        db (db/new-db data)
        toon (toon/new-random-toon db)]
   (println (toon/toon->str toon))))
