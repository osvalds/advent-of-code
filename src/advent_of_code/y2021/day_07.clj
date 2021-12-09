(ns advent-of-code.y2021.day-07
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

(defn parse-input [input]
  (->>
    (str/split input #",")
    (map #(Integer/parseInt %))
    (group-by identity)
    (sort-by key)))

(defn partial-sum [n]
  (/ (* n (+ n 1)) 2))

(defn calculate-fuel [position crab-positions]
  (apply + (map (fn [[idx crabs]]
                  (Math/abs ^int (* (- position idx) (count crabs))))
             crab-positions)))

(defn calculate-fuel-partial [position crab-positions]
  (apply + (map (fn [[idx crabs]]
                  (Math/abs ^int (* (partial-sum (Math/abs ^int (- position idx))) (count crabs))))
             crab-positions)))

(defn part-1
  "Day 07 Part 1"
  [input]
  (let [crab-positions (parse-input input)
        all-positions (range 0 (+ 1 (key (last crab-positions))))]
    (val (first (sort-by val (reduce
                               (fn [m position]
                                 (let [fuel-costs (calculate-fuel position crab-positions)]
                                   (assoc m position fuel-costs)))
                               {}
                               all-positions))))))

(defn part-2
  "Day 07 Part 2"
  [input]
  (let [crab-positions (parse-input input)
        all-positions (range 0 (+ 1 (key (last crab-positions))))]
    (val (first (sort-by val (reduce
                               (fn [m position]
                                 (let [fuel-costs (calculate-fuel-partial position crab-positions)]
                                   (assoc m position fuel-costs)))
                               {}
                               all-positions))))))
