(ns advent-of-code.y2021.day-13
  (:require [clojure.string :as str]))

(defn vec2d [x y val] (vec (repeat y (vec (repeat x val)))))

(defn parse-coord [c] (mapv #(Integer/parseInt %) c))

(defn parse-fold [f]
  (let [[axis pos] (str/split f #"=")]
    [axis (Integer/parseInt pos)]))

(defn parse [input]
  (let [lines (str/split-lines input)
        split-position (.indexOf lines "")
        [coords folds] (split-at split-position lines)]
    [(map #(parse-coord (str/split % #",")) coords)
     (map #(parse-fold (last (str/split % #" "))) (rest folds))]))

(defn get-dimensions [coords]
  [(inc (apply max (map first coords))) (inc (apply max (map second coords)))])

(defn do-fold [coordinates [axis fold-pos]]
  (if (= axis "x")
    (map
      (fn [[x y]]
        (if (< fold-pos x)
          [(- fold-pos (- x fold-pos)) y]
          [x y]))
      coordinates)
    (map
      (fn [[x y]]
        (if (< fold-pos y)
          [x (- fold-pos (- y fold-pos))]
          [x y]))
      coordinates)))

(defn part-1
  "Day 13 Part 1"
  [input]
  (let [[coordinates folds] (parse input)]
    (count (into #{} (do-fold coordinates (first folds))))))

(defn print-key [coords]
  (let [[maxx maxy] (get-dimensions coords)
        printer (vec2d maxx maxy ".")
        final-board (reduce
                      (fn [board [x y]]
                        (assoc-in board [y x] "#"))
                      printer
                      coords)]
    (println (str/join "\n" (map #(str/join %) final-board)))))

(defn part-2
  "Day 13 Part 2"
  [input]
  (let [[coordinates folds] (parse input)
        final-coords (reduce
                       (fn [coords fold]
                         (into #{} (do-fold coords fold)))
                       (into #{} coordinates)
                       folds)]
    (print-key final-coords)))


