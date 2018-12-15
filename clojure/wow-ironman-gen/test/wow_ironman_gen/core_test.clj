(ns wow-ironman-gen.core-test
  (:use clojure.test
        wow-ironman-gen.core))

(deftest races-test
  (testing "races are sane"
    (is (= (set (keys classes-for-race))
           (set (races))))))

(deftest cls-test
  (testing "cls has reasonable results"
    (is (contains? (set (classes-for-race :gnome))
                   (cls :gnome)))))

(deftest no-gnome-druid-test
  (testing "gnomes cannot be druids, evar"
    (is (not (contains? (set (classes-for-race :gnome))
                        :druid)))))

(deftest gender-test
  (testing "gender is female or male"
    (is (#{:female :male} (gender)))))

(deftest faction-test
  (testing "faction is horde or ... alliance, psshh"
    (is (#{:horde :alliance} (faction)))))

(deftest pandaren-present-toon-has-faction
  (testing "pandaren toons should have faction in present-toon"
    (let [t {:race    :pandaren
             :cls     :monk
             :gender  :female
             :faction :horde}]
      (is (= (present-toon t)
             "female horde pandaren monk")))))

(deftest non-pandaren-present-toon-lacks-faction
  (testing "non-pandaren toons should lack faction in present-toon"
    (let [t {:race    :tauren
             :cls     :hunter
             :gender  :female
             :faction :horde}]
      (is (= (present-toon t)
             "female tauren hunter")))))
