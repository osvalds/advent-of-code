(ns advent-of-code.y2020.day-01
  (:require [clojure.string :as str]))

(defn find-pair [numbers target]
  (loop [idx 0]
    (let [curr (get numbers idx)
          has-other (some #{(- target curr)} numbers)]
      (if (and (not has-other) (< idx (dec (count numbers))))
        (recur (inc idx))
        (when has-other
          [(- target curr) curr])))))

(defn part-1
  "Day 25 Part 1"
  [input]
  (let [nums (mapv #(Integer/parseInt %) (str/split-lines input))]
    (apply * (find-pair nums 2020))))

(defn part-2
  "Day 25 Part 2"
  [input]
  (let [nums (mapv #(Integer/parseInt %) (str/split-lines input))]
    (loop [idx 0]
      (let [curr (get nums idx)
            target (- 2020 curr)
            pair (find-pair (subvec nums (inc idx)) target)]
        (if (and (not pair) (< idx (count nums)))
          (recur (inc idx))
          (when pair
            (* curr (first pair) (last pair))))))))
