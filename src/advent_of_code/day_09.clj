(ns advent-of-code.day-09
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-line [line]
  (mapv #(Integer/parseInt %) (str/split line #"")))

(defn row-low-coord [heatmap y]
  (let [row (get heatmap y)]
    (map-indexed
      (fn [x item]
        (let [n (get-in heatmap [(dec y) x])
              e (get row (inc x))
              s (get-in heatmap [(inc y) x])
              w (get row (dec x))
              comp (sort (remove nil? [n e s w]))]
          (when (< item (first comp)) [y x])))
      row)))

(defn get-map-low-coord [heatmap]
  (loop [idx 0
         result []]
    (if (< idx (count heatmap))
      (recur (inc idx) (into result (remove nil? (row-low-coord heatmap idx))))
      result)))

(defn part-1
  "Day 09 Part 1"
  [input]
  (let [heatmap (->> input (str/split-lines) (mapv parse-line))
        low-coords (get-map-low-coord heatmap)]
    (apply + (map #(inc (get-in heatmap %)) low-coords))))

(defn get-adj [heatmap [y x]]
  (let [n [(dec y) x]
        e [y (inc x)]
        s [(inc y) x]
        w [y (dec x)]]
    (filter #(< (get-in heatmap % 10) 9) [n e s w]))

  (defn basin-set [heatmap [y x] visited]
    (let [adjacent (get-adj heatmap [y x])
          need-to-visit (set/difference (set adjacent) visited)]
      (if (= 0 (count need-to-visit))
        visited
        (reduce
          (fn [visited-r [y1 x1]]
            (basin-set heatmap [y1 x1] (conj visited-r [y1 x1])))
          visited
          need-to-visit)))))

(defn part-2
  "Day 09 Part 2"
  [input]
  (let [heatmap (->> input (str/split-lines) (mapv parse-line))]
    (->> heatmap
      (get-map-low-coord)
      (map #(count (basin-set heatmap % (set [%]))))
      (sort)
      (take-last 3)
      (apply *))))
