(ns advent-of-code.y2021.day-25
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

(defn parse [input]
  (mapv #(str/split % #"") (str/split-lines input)))

(defn move-row-east [row]
  (let [xs (range (count row))]
    (reduce
      (fn [result-row x]
        (let [cc (get row x)
              ncc (get row (rem (+ x 1) (count row)))]
          (if (and (= cc ">") (= ncc "."))
            (-> result-row
              (assoc x ".")
              (assoc (rem (inc x) (count row)) ">"))
            result-row)))
      row
      xs)))

(defn move-east [board]
  (reduce
    (fn [result row]
      (conj result (move-row-east row)))
    []
    board))

(defn move-south [board]
  (let [yrange (range (count board))
        xrange (range (count (first board)))
        coords (for [y yrange x xrange] [y x])]
    (reduce
      (fn [result-board [y x]]
        (let [cc (get-in board [y x])
              ncc (get-in board [(rem (inc y) (count board)) x])]
          (if (and (= cc "v") (= ncc "."))
            (-> result-board
              (assoc-in [y x] ".")
              (assoc-in [(rem (inc y) (count result-board)) x] "v"))
            result-board)))
      board
      coords)))

(defn part-1
  "Day 25 Part 1"
  [input]
  (loop [i 0
         board (parse input)]
    (let [next-board (move-south (move-east board))]
      (if (= board next-board)
        (+ 1 i)
        (recur (inc i) next-board)))))

(defn part-2
  "Day 25 Part 2"
  [input]
  input)

