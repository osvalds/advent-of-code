(ns advent-of-code.y2020.day-05
  (:require [clojure.string :as str]))

(defn parse-row [row]
  (map #(if (#{"F" "L"} %) 0 1) (str/split row #"")))

(defn parse [input]
  (map parse-row (str/split-lines input)))

(defn reduce-range [fl ids]
  (if (seq fl)
    (reduce-range (rest fl) (get (vec (partition (/ (count ids) 2) ids)) (first fl)))
    (first ids)))

(defn seat-id [pass]
  (let [[rows cols] (vec (partition 7 7 nil pass))]
    (+ (* 8 (reduce-range rows (range 128)))
      (reduce-range cols (range 8)))))

(defn part-1
  "Day 04 Part 1"
  [input]
  (let [parsed (parse input)]
    (apply max (map seat-id parsed))))


(defn part-2
  "Day 04 Part 2"
  [input]
  (let [parsed (parse input)]
    (->> parsed
      (map seat-id)
      (sort)
      (partition 2 1)
      (filter #(= 2 (- (last %) (first %))))
      (first)
      (first)
      (inc))))


