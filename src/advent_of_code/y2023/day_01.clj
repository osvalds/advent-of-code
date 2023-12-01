(ns advent-of-code.y2023.day-01
  (:require [clojure.string :as s]))

;; --- Day 1: Trebuchet?! ---


(defn part-1
  "Day 1 Part 1"
  [input]
  (let [nums (mapv #(re-seq #"\d" %) (s/split-lines input))]
    (reduce (fn [acc nums]
              (let [f (Integer/parseInt (first nums))
                    s (Integer/parseInt (last nums))]
                (+ acc (* 10 f) s)))
            0
            nums)))

(def num->int
  {"one" 1
   "two" 2
   "three" 3
   "four" 4
   "five" 5
   "six" 6
   "seven" 7
   "eight" 8
   "nine" 9})

(def str-or-int
  (fn [x]
    (or (num->int x) (Integer/parseInt x))))

(defn part-2
  "Day 1 Part 2"
  [input]
  (let [nums (mapv #(->> %
                         (re-seq #"(?=(\d|one|two|three|four|five|six|seven|eight|nine))")
                         (flatten)
                         rest)
                   (s/split-lines input))]
    (reduce (fn [acc nums]
              (let [f (str-or-int (first nums))
                    l (str-or-int (last nums))]
                (+ acc (* 10 f) l)))
            0
            nums)))