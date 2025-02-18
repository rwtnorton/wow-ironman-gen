(ns wow-ironman-gen.loader
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:import [java.io PushbackReader]))

(def ^:const default-resource-name "by-race.edn")

(defn load-edn
  ([src]
   (try
     (if-let [r (io/resource src)]
       (with-open [rdr (io/reader r)]
         (edn/read (PushbackReader. rdr)))
       (throw (ex-info (format "no such resource: %s" src)
                       {:src src})))))
  ([]
   (load-edn default-resource-name)))
