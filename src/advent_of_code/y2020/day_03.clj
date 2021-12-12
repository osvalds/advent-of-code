(ns advent-of-code.y2020.day-03
  (:require [clojure.string :as str]))

(defn travel-slope [map right down]
  (let [cols (count (first map))
        rows (count map)]
    (loop [x 0
           y 0
           trees 0]
      (if (< y rows)
        (let [has-tree (= \# (get-in map [y (rem x cols)]))]
          (recur (+ x right) (+ y down) (cond-> trees has-tree (inc))))
        trees))))

(defn part-1
  "Day 25 Part 1"
  [input]
  (let [map (str/split-lines input)]
    (travel-slope map 3 1)))


(defn part-2
  "Day 25 Part 2"
  [input]
  (let [map (str/split-lines input)]
    (*
      (travel-slope map 1 1)
      (travel-slope map 3 1)
      (travel-slope map 5 1)
      (travel-slope map 7 1)
      (travel-slope map 1 2))))

