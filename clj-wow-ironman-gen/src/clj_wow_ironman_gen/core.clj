(ns clj-wow-ironman-gen.core)

;;
;; Randomly generate a World of Warcraft race/class/gender combo for the
;; WoW Ironman Challenge.
;;

;; human, night_elf, dwarf, gnome, draenei, worgen, pandaren,
;;  orc, troll, forsaken, tauren, blood_elf, goblin
;; No death knights.
;; warrior, paladin, hunter, shaman, druid, rogue, monk, mage, warlock, priest

(defn sample-gen []
  (let [rand (java.util.Random.)]
    (fn [coll]
      (let [r (.nextInt rand)
            n (count coll)
            which (mod r n)]
        (nth coll which)))))

(def sample (sample-gen))

(def classes-for-race
  {
   :human [:warrior :paladin :hunter :rogue :monk :mage :warlock :priest],
   :night_elf [:warrior :hunter :rogue :druid :monk :mage :priest],
   :dwarf [:warrior :paladin :hunter :shaman :rogue :monk :mage :warlock
           :priest],
   :gnome [:warrior :rogue :monk :mage :warlock :priest]
   :draenei [:warrior :paladin :hunter :shaman :monk :mage :priest],
   :worgen [:warrior :hunter :druid :rogue :mage :warlock :priest],
   :pandaren [:warrior :hunter :shaman :rogue :monk :mage :priest],
   :orc [:warrior :hunter :shaman :rogue :monk :mage :warlock],
   :troll [:warrior :hunter :shaman :druid :rogue :monk :mage :warlock :priest],
   :forsaken [:warrior :hunter :rogue :monk :mage :warlock :priest],
   :tauren [:warrior :paladin :hunter :shaman :druid :monk :priest],
   :blood_elf [:warrior :paladin :hunter :rogue :monk :mage :warlock :priest],
   :goblin [:warrior :hunter :shaman :rogue :mage :warlock :priest],
   })

(defn races [] (keys classes-for-race))

(defn race [] (sample (races)))

(defn cls [race] (sample (classes-for-race race)))

(defn gender [] (sample [:female, :male]))

(defn faction [] (sample [:horde, :alliance]))

(defn toon []
  (let [r (race)
        c (cls r)
        g (gender)
        f (faction)]
    {:race r, :cls c, :gender g, :faction f}))

(defn present-toon [toon]
  (let [{r :race, c :cls, g :gender, f :faction} toon
        attrs (if (= r :pandaren) (list g f r c) (list g r c))
        attr->str #(subs (str %) 1)]
    (clojure.string/join " " (map attr->str attrs))))

(defn -main [& args]
  (println (present-toon (toon))))
