(ns advent-of-code.day-01
  (:require [clojure.string :as s]))

;; "Day 1: Sonar Sweep"

(defn count-increasing-sums [int-array]
  (let [input-range (range 1 (count int-array))]
    (->> input-range
      (filter
        (fn [index]
          (let [curr (nth int-array index)
                prev (nth int-array (- index 1))]
            (< prev curr))))
      count)))

(defn part-1 [input]
  (let [input-array (map #(Integer/parseInt %) (s/split-lines input))]
    (count-increasing-sums input-array)))

(defn part-2 [input]
  (let [input-array (map #(Integer/parseInt %) (s/split-lines input))
        ;; https://stackoverflow.com/a/1430360
        input-3-sum (map (partial apply +) (partition 3 1 input-array))]
    (count-increasing-sums input-3-sum)))