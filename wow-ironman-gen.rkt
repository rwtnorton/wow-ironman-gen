#lang racket

;;
;; Randomly generate a World of Warcraft race/class/gender combo for the
;; WoW Ironman Challenge.
;;

(define races
  '("human" "night_elf" "dwarf" "gnome" "draenei" "worgen" "pandaren"
    "orc" "troll" "forsaken" "tauren" "blood_elf" "goblin"))

;; No death knights.
(define classes
  '("warrior" "paladin" "hunter" "shaman" "druid"
    "rogue" "monk" "mage" "warlock" "priest"))

(define (make-toon race class gender faction)
  (lambda (accessor) (accessor race class gender faction)))

(define (toon-race toon)
  (let ((race-accessor (lambda (r c g f) r)))
    (toon race-accessor)))
(define (toon-class toon)
  (let ((class-accessor (lambda (r c g f) c)))
    (toon class-accessor)))
(define (toon-gender toon)
  (let ((gender-accessor (lambda (r c g f) g)))
    (toon gender-accessor)))
(define (toon-faction toon)
  (let ((faction-accessor (lambda (r c g f) f)))
    (toon faction-accessor)))
(define (toon-dual-faction? toon) (equal? (toon-race toon) "pandaren"))

(define (sample lst)
  (list-ref lst (random (length lst))))

(define classes-for-race
  (hash
   "human" '("warrior" "paladin" "hunter" "rogue" "monk" "mage" "warlock"
             "priest")
   "night_elf" '("warrior" "hunter" "rogue" "druid" "monk" "mage" "priest")
   "dwarf"  '("warrior" "paladin" "hunter" "shaman" "rogue" "monk" "mage"
              "warlock" "priest")
   "gnome"  '("warrior" "rogue" "monk" "mage" "warlock" "priest")
   "draenei" '("warrior" "paladin" "hunter" "shaman" "monk" "mage" "priest")
   "worgen" '("warrior" "hunter" "druid" "rogue" "mage" "warlock" "priest")
   "pandaren" '("warrior" "hunter" "shaman" "rogue" "monk" "mage" "priest")
   "orc" '("warrior" "hunter" "shaman" "rogue" "monk" "mage" "warlock")
   "troll" '("warrior" "hunter" "shaman" "druid" "rogue" "monk" "mage"
             "warlock" "priest")
   "forsaken" '("warrior" "hunter" "rogue" "monk" "mage" "warlock" "priest")
   "tauren" '("warrior" "paladin" "hunter" "shaman" "druid" "monk" "priest")
   "blood_elf" '("warrior" "paladin" "hunter" "rogue" "monk" "mage" "warlock"
                 "priest")
   "goblin" '("warrior" "hunter" "shaman" "rogue" "mage" "warlock" "priest")))

(define (random-race) (sample races))
(define (random-class race)
  (let ((class-list (hash-ref classes-for-race race)))
    (sample class-list)))
(define (random-gender) (sample '("female" "male")))
(define (random-faction) (sample '("horde" "alliance")))
(define (random-toon)
  (let ((r (random-race))
        (g (random-gender))
        (f (random-faction)))
    (let ((c (random-class r)))
      (make-toon r c g f))))
(define (format-toon toon)
  (let ((r (toon-race toon))
        (c (toon-class toon))
        (g (toon-gender toon))
        (f (toon-faction toon)))
    (let ((attrs (if (toon-dual-faction? toon) (list g f r c) (list g r c))))
      (string-join attrs " "))))
(define (main)
  (display (format-toon (random-toon)))
  (newline))
(main)
