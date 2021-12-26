(ns advent-of-code.y2021.day-20
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

(defn parse [input]
  (let [[algorithm image] (str/split input #"\n\n")]
    [(mapv #(if (= % \.) 0 1) algorithm)
     (mapv (fn [row] (mapv #(if (= % \.) 0 1) row)) (str/split-lines image))]))

(def neighbor-diff [[-1 -1] [-1 0] [-1 1] [0 -1] [0 0] [0 1] [1 -1] [1 0] [1 1]])

(defn neighbors [y x]
  (mapv (fn [[y' x']] [(+ y y') (+ x x')]) neighbor-diff))

(defn all-coords [image]
  (let [w (count (first image))
        h (count image)]
    (for [y (range h) x (range w)] [y x])))

(defn pad-image [image algorithm iteration]
  (let [default (if (= 0 (first algorithm))
                  0
                  (nth algorithm (if (even? iteration) 511 0)))
        w (count (first image))]
    (vec (concat
           [(vec (repeat (+ 2 w) default))]
           (mapv (fn [row]
                   (apply conj [default] (conj row default))) image)
           [(vec (repeat (+ 2 w) default))]))))

(defn enhance [algorithm image step]
  (let [padded-image (pad-image image algorithm step)
        next-image (reduce
                     (fn [result-image [y x]]
                       (let [binary (Integer/parseInt (apply str (map #(get-in padded-image % (if (= 0 (first algorithm))
                                                                                                0
                                                                                                (nth algorithm (if (even? step) 511 0)))) (neighbors y x))) 2)
                             new-pixel (nth algorithm binary)]
                         (assoc-in result-image [y x] new-pixel)))
                     padded-image
                     (all-coords padded-image))]
    next-image))

(defn solve [input times]
  (let [[algorithm image] (parse input)]
    (loop [step 0
           image image]
      (if (< step times)
        (recur (inc step) (enhance algorithm image step))
        (count (filter #(= % 1) (flatten image)))))))

(defn part-1
  "Day 20 Part 1"
  [input]
  (solve input 2))

(defn part-2
  "Day 20 Part 2"
  [input]
  (solve input 50))
