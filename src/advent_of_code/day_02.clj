(ns advent-of-code.day-02
  (:require [clojure.string :as s]))

(defn parse-input-line [line]
  (let [[direction distance] (s/split line #" ")]
    [direction (Integer/parseInt distance)]))

(defn direction-sum [grouped-directions direction]
  (apply + (map second (get grouped-directions direction))))

(defn part-1
  "Day 02 Part 1"
  [input]
  (let [parsed (map parse-input-line (s/split-lines input))
        directions (group-by first parsed)
        forward-sum (direction-sum directions "forward")
        down-sum (direction-sum directions "down")
        up-sum (direction-sum directions "up")]
    (* forward-sum (- down-sum up-sum))))


(defn part-2
  "Day 02 Part 2"
  [input]
  (let [parsed (map parse-input-line (s/split-lines input))
        [_ h d] (reduce (fn [[aim horizontal depth] [direction x]]
                          (condp = direction
                            "down" [(+ aim x) horizontal depth]
                            "up" [(- aim x) horizontal depth]
                            "forward" [aim (+ horizontal x) (+ depth (* aim x))]))
                  [0 0 0]
                  parsed)]
    (* h d)))
