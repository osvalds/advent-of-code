(ns advent-of-code.y2021.day-05
  (:require [clojure.string :as s]
            [clojure.pprint :refer [pprint]]))

(defn vec2d [x y val] (vec (repeat y (vec (repeat x val)))))

(defn get-xy [xys]
  (let [[x y] (s/split xys #",")]
    [(Integer/parseInt x) (Integer/parseInt y)]))

(defn parse-line [line-string]
  (let [[p1 p2] (s/split line-string #"\s->\s")]
    [(get-xy p1) (get-xy p2)]))

(defn hor? [[[_ y1] [_ y2]]] (= y1 y2))

(defn ver? [[[x1] [x2]]] (= x1 x2))

;; H to The Izzo
(defn is-hov? [[[x1 y1] [x2 y2]]] (or (= x1 x2) (= y1 y2)))

(defn abs [x]
  (Math/abs ^int x))

(defn is-diag? [[[x1 y1] [x2 y2]]] (or
                                     (= (+ x1 y1) (+ x2 y2))
                                     (= (abs (- x2 y2)) (abs (- x1 y1)))))


(defn verstappen [[x y] [mx my]] [(max x mx) (max y my)])

(defn max-coord [line mp]
  (reduce (fn [p m] (verstappen p m)) mp line))

(defn sort-coord [line] (vec (if (hor? line) (sort-by first line) (sort-by second line))))

(defn get-legit-input [lines legit?]
  (reduce (fn [result line]
            (let [[plines max] result
                  pline (parse-line line)]
              (if (legit? pline)
                [(conj plines pline) (max-coord pline max)]
                result)))
    [[] [0 0]]
    lines))

(defn update-hline [line x1 x2]
  (let [[start end] (if (< x1 x2) [x1 x2] [x2 x1])]
    (vec (map-indexed (fn [idx val] (if (<= start idx end) (inc val) val)) line))))

(defn update-hor [board [[x1 y1] [x2 _]]]
  (map-indexed (fn [idx line]
                 (if (= idx y1)
                   (update-hline line x1 x2)
                   line))
    board))

(defn update-ver [board [[x1 y1] [_ y2]]]
  (let [[start end] (if (< y1 y2) [y1 y2] [y2 y1])]
    (map-indexed (fn [idx line]
                   (if (<= start idx end)
                     (update line x1 inc)
                     line))
      board)))

(defn part-1
  "Day 05 Part 1"
  [input]
  (let [[hv-lines [mx my]] (get-legit-input (s/split-lines input) is-hov?)
        start (vec2d (inc mx) (inc my) 0)
        board (reduce
                (fn [result line]
                  (if (hor? line)
                    (update-hor result line)
                    (update-ver result line)))
                start
                hv-lines)]
    (count (filter #(> % 1) (apply concat board)))))

(defn update-diag [board input-line]
  (let [[[x1 y1] [x2 y2]] (vec (sort-by second input-line))
        xrange (if (<= x1 x2) (range x1 (inc x2)) (range x1 (dec x2) -1))]
    (map-indexed (fn [idx line]
                   (if (<= y1 idx y2)
                     (update line (nth xrange (abs (- idx y1))) inc)
                     line))
      board)))

(defn part-2
  "Day 05 Part 2"
  [input]
  (let [[hvd-lines [mx my]] (get-legit-input (s/split-lines input) #(or (is-hov? %) (is-diag? %)))
        start (vec2d (inc mx) (inc my) 0)
        board (reduce
                (fn [result line]
                  (cond
                    (hor? line) (update-hor result line)
                    (ver? line) (update-ver result line)
                    :else (update-diag result line)))
                start
                hvd-lines)]
    (count (filter #(> % 1) (apply concat board)))))
