(ns duolingo.core
  (:gen-class)
  (:require [clojure.string :as string]))

;; 1. print a puzzle (print options)
;; 2. accept input (an answer)  (read-line)
;; 3. check if it's correct
;; 4. loop

(defn generate-puzzle
  []
  {:format :multi-choice
   :question "Does this even work?"
   :options {:a "First attempt -- nope"
             :b "Made some progress -- still nope"
             :c "Ask the Googles"
             :d "Victory!  Next Step: World Domination"}
   :answer #{"D" "d"}})

(def puzzle (generate-puzzle))


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

  ; (contains? #{:a :b :c} :b))


(defn -main
  [& args]
  (loop []
    (let [puzzle (generate-puzzle)]
      (print-question puzzle)
      (let [answer (read-line)]
        (if (check-answer puzzle answer)
          (println "Correct! Go forth and conquer.")
          (println "You Chose . . .  Poorly"))))))
;; TODO: look at (loop) and (recur)
