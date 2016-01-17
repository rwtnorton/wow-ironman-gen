(defproject wow-ironman-gen "0.1.0-SNAPSHOT"
  :description "WoW Ironman Char Generator"
  :url "https://github.com/rwtnorton/wow-ironman-gen/clj_wow_ironman_gen"
  :license {:name "GNU Public License v3"
            :url "https://www.gnu.org/licenses/gpl"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  ; :plugins [[lein-swank "1.4.4"]]
  :aot [wow-ironman-gen.core]
  :main wow-ironman-gen.core)
