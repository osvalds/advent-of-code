(ns advent-of-code.y2021.day-11
  (:require [clojure.string :as str]))

(defn parse-line [line] (mapv #(Integer/parseInt %) (str/split line #"")))

(defn parse [input] (mapv parse-line (str/split-lines input)))

(defn inc-grid [grid]
  (mapv (fn [row] (mapv inc row)) grid))

(defn neighbor-coords [[y x]]
  (let [n [(dec y) x]
        ne [(dec y) (inc x)]
        e [y (inc x)]
        se [(inc y) (inc x)]
        s [(inc y) x]
        sw [(inc y) (dec x)]
        w [y (dec x)]
        nw [(dec y) (dec x)]]
    [n ne e se s sw w nw]))

(defn to-be-flashed-row [y row flashed]
  (second (reduce (fn [[x flashed-set] octopus]
                    (if (and (> octopus 9) (not (contains? flashed-set [y x])))
                      [(inc x) (conj flashed-set [y x])]
                      [(inc x) flashed-set]))
            [0 flashed]
            row)))

(defn to-be-flashed [grid]
  (second (reduce
            (fn [[y flashed] row]
              (let [flashed-in-row (to-be-flashed-row y row flashed)]
                [(inc y) (into flashed flashed-in-row)]))
            [0 #{}]
            grid)))

(def safe-inc (fnil inc 0))

(defn flash-grid [grid needs-flashing flashed]
  (let [needs-incrementing (filter
                             #(and
                                (every? (fn [xy] (< -1 xy (count grid))) %)
                                (not (contains? flashed %)))
                             (apply concat (map neighbor-coords needs-flashing)))
        inced-grid (reduce
                     (fn [grid coords] (update-in grid coords safe-inc))
                     grid
                     needs-incrementing)]
    (reduce (fn [grid coords] (assoc-in grid coords 0)) inced-grid needs-flashing)))

(defn step [grid]
    (loop [flashed #{}
           iter-grid (inc-grid grid)]
      (let [need-to-be-flashed (to-be-flashed iter-grid)]
        (if (> (count need-to-be-flashed) 0)
          (recur (into flashed need-to-be-flashed) (flash-grid iter-grid need-to-be-flashed flashed))
          [flashed iter-grid]))))

(defn part-1
  "Day 11 Part 1"
  [input]
  (let [octopuses (parse input)]
    (loop [i 0
           flashes 0
           grid octopuses]
      (let [[flashed-octopuses next-grid] (step grid)]
        (if (< i 100)
          (recur (inc i) (+ flashes (count flashed-octopuses)) next-grid)
          flashes)))))

(defn part-2
  "Day 11 Part 2"
  [input]
  (let [octopuses (parse input)]
    (loop [i 0
           grid octopuses]
      (let [[flashed-octopuses next-grid] (step grid)]
        (if (< (count flashed-octopuses) 100)
          (recur (inc i)  next-grid)
          (inc i))))))
