(ns duolingo.core
  (:gen-class)
  (:require [clojure.string :as string]))

;; 1. print a puzzle (print options)
;; 2. accept input (an answer)  (read-line)
;; 3. check if it's correct
;; 4. loop

;;Make this work with different number of puzzle options
;;Make this work with different puzzel formats
;;When correct, loop and regenerate puzzle (end after what?)
;;Keep track of progress

;Questions for Jon:
;1.  How do I change the name of a project?  Do I change all the internal references one by one?
;2.  How do I put this on Github?


(def puzzles [
              {:format :multi-choice
               :question "Q1:  Does this even work?"
               :options {:a "First attempt -- nope"
                         :b "Made some progress -- still nope"
                         :c "Ask the Googles"
                         :d "Victory!  Next Step: World Domination"}
               :answer #{"D" "d"}}
              {:format :multi-choice
               :question "Q2:  Will this work without four options?"
               :options {:a "First attempt -- nope"
                         :b "Made some progress -- still nope"
                         :c "Ask the Googles"
                         :d "Victory!  Next Step: World Domination"}
               :answer #{"A" "a"}}
              {:format :multi-choice
               :question "Q3:  Can I make these questions a different format?"
               :options {:a "First attempt -- nope"
                         :b "Made some progress -- still nope"
                         :c "Ask the Googles"
                         :d "Victory!  Next Step: World Domination"}
               :answer #{"C" "c"}}])

(defn generate-puzzle
  []
  (get puzzles (rand-int 3)))
  ;generate random number to pick a puzzle

(defn print-options
  [puzzle]
  (do
    (println (str 'A- (get-in puzzle [:options :a])))
    (println (str 'B- (get-in puzzle [:options :b])))
    (println (str 'C- (get-in puzzle [:options :c])))
    (println (str 'D- (get-in puzzle [:options :d])))))

(defn print-question
  [puzzle]
  (do
    (println (:question puzzle))
    (print-options puzzle)))

(defn check-answer
  [puzzle answer]
  (contains? (get-in puzzle [:answer]) answer))

(defn -main
  [& args]
  (loop [remaining-puzzles puzzles]
    (print-question (nth remaining-puzzles 0))
    (let [answer (read-line)]
      (if (check-answer (nth remaining-puzzles 0) answer)
        (do
          (println "--Great, kid, but don't get cocky.")
          (if (empty? remaining-puzzles)
            (println "You've completed your quest, but your princess is in another castle.")
            (let [[puzzle & remaining] remaining-puzzles]
              (recur remaining))))
        (do
          (println "--You Chose . . .  Poorly")
          (recur remaining-puzzles))))))
