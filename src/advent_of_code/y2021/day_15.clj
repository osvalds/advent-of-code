(ns advent-of-code.y2021.day-15
  (:require [clojure.string :as str]))

(defn get-adj [risk-scores [y x]]
  (let [n [(dec y) x]
        e [y (inc x)]
        s [(inc y) x]
        w [y (dec x)]]
    (->> [n e s w]
      (map (fn [yx] [yx (get-in risk-scores yx 10)]))
      (filter #(< (second %) 10))
      (into {}))))

(defn parse-input [input]
  (mapv (fn [row] (mapv #(Integer/parseInt %) (str/split row #""))) (str/split-lines input)))

(defn process-row [y risk-scores]
  (loop [x 0
         row {}]
    (if (< x (count risk-scores))
      (recur (inc x) (assoc row [y x] (get-adj risk-scores [y x])))
      row)))

(defn input->graph [risk-scores]
  (loop [y 0
         graph {}]
    (if (< y (count risk-scores))
      (recur (inc y) (into graph (process-row y risk-scores)))
      graph)))

(defn advance-frontier [frontier came-from cost-so-far graph]
  (let [sorted-frontier (sort-by second frontier)
        [current] (first sorted-frontier)
        neighbors (get graph current)
        n-frontier (rest sorted-frontier)]
    (reduce
      (fn [[frontier came-from cost-so-far] [next]]
        (let [new-cost (+ (get cost-so-far current 0) (get-in graph [current next]))]
          (if (or
                (not (get cost-so-far next))
                (< new-cost (get cost-so-far next)))
            (let [n-cost-so-far (assoc cost-so-far next new-cost)
                  n-frontier2 (conj frontier [next new-cost])
                  n-came-from (assoc came-from next current)]
              [n-frontier2 n-came-from n-cost-so-far])
            [frontier came-from cost-so-far])))
      [n-frontier came-from cost-so-far]
      neighbors)))

(defn dijkstra [graph start target]
  (loop [frontier [[start 0]]
         came-from {start nil}
         cost-so-far {start 0}]
    (if (< 0 (count frontier))
      (let [[front from cost] (advance-frontier frontier came-from cost-so-far graph)]
        (recur front from cost))
      (get cost-so-far target))))

(defn part-1
  "Day 15 Part 1"
  [input]
  (let [risk-scores (parse-input input)
        graph (input->graph risk-scores)
        start [0 0]
        target [(dec (count risk-scores)) (dec (count risk-scores))]]
    (dijkstra graph start target)))

(defn incr-2d [risk-score i]
  (mapv (fn [row] (mapv #(+ 1 (rem (+ i %) 9)) row)) risk-score))

(defn tile-y [risk-score]
  (loop [i 0
         result risk-score]
    (if (< i 4)
      (recur (inc i) (into result (incr-2d risk-score i)))
      result)))

(defn tile-x [risk-score]
  (loop [i 0
         result risk-score]
    (if (< i 4)
      (recur (inc i) (mapv (fn [a b] (into a b)) result (incr-2d risk-score i)))
      result)))

(defn part-2
  "Day 15 Part 2"
  [input]
  (let [risk-scores (parse-input input)
        tiled (tile-x (tile-y risk-scores))
        target [(dec (count tiled)) (dec (count tiled))]]
    (dijkstra (input->graph tiled) [0 0] target)))

