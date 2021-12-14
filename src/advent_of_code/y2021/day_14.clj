(ns advent-of-code.y2021.day-14
  (:require [clojure.string :as str]))

(defn parse [input]
  (let [[template r] (str/split input #"\n\n")
        rules (mapv #(str/split % #" -> ") (str/split-lines r))]
    [template
     (reduce (fn [result [adj insert]] (assoc result adj (.charAt insert 0))) {} rules)]))

(defn insert [pairs rules]
  (reduce
    (fn [result pair]
      (let [new-letter (get rules (str/join pair))]
        (if new-letter
          (conj result [(first pair) new-letter] [new-letter (second pair)])
          (conj result pair))))
    []
    pairs))

(defn pairs->list [pairs]
  (concat [] (first pairs) (mapv second (rest pairs))))

(defn freq-diff [pairs]
  (let [freqs (sort-by val (frequencies (pairs->list pairs)))
        min (val (first freqs))
        max (val (last freqs))]
    (- max min)))

(defn solve [input iterations]
  (let [[template rules] (parse input)
        pairs (partition 2 1 template)]
    (loop [i 0
           pairs pairs]
      (if (< i iterations)
        (recur (inc i) (insert pairs rules))
        (freq-diff pairs)))))

(defn part-1
  "Day 14 Part 1"
  [input]
  (solve input 10))

(defn part-2
  "Day 14 Part 2"
  [input]
  (solve input 20))
