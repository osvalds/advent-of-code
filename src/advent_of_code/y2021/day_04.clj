(ns advent-of-code.y2021.day-04
  (:require [clojure.string :as s]
            [clojure.pprint :refer [pprint]]))

(defn coll-sum [coll] (apply + (remove nil? coll)))

(defn board-sum [board]
  (coll-sum (map coll-sum board)))

(defn parse-int-line [numbers separator]
  (mapv #(Integer/parseInt %) (s/split (s/trim numbers) separator)))

(defn build-board [list-of-numbers]
  (mapv #(parse-int-line % #" +") list-of-numbers))

(defn parse-boards [input]
  (mapv build-board (partition 5 6 input)))

(defn mark-number [board number]
  (mapv (fn [line] (mapv #(if (= % number) nil %) line)) board))

(defn complete? [row] (every? nil? row))

(defn cols->rows [board]
  (vec (map-indexed (fn [idx _] (mapv #(get % idx) board)) board)))

(defn board-complete [board]
  (let [row-complete? (some complete? board)
        col-complete? (some complete? (cols->rows board))]
    (when (or row-complete? col-complete?) board)))

(defn get-numbers-and-boards [input]
  (let [split-lines (s/split-lines input)
        drawn-numbers (parse-int-line (first split-lines) #",")
        boards (parse-boards (drop 2 split-lines))]
    [drawn-numbers boards]))

(defn part-1
  "Day 04 Part 1"
  [input]
  (let [[drawn-numbers boards] (get-numbers-and-boards input)]
    (loop [numbers drawn-numbers
           boards boards]
      (let [drawn (first numbers)
            advanced-boards (mapv #(mark-number % drawn) boards)
            completed-board (some board-complete advanced-boards)]
        (if completed-board
          (* (board-sum completed-board) drawn)
          (recur (drop 1 numbers) advanced-boards))))))

(defn play-until-end [numbers board]
  (loop [numbers numbers
         board board]
    (let [drawn (first numbers)
          advanced-board (mark-number board drawn)]
      (if (board-complete advanced-board)
        (* (board-sum advanced-board) drawn)
        (recur (drop 1 numbers) advanced-board)))))

(defn part-2
  "Day 04 Part 2"
  [input]
  (let [[drawn-numbers boards] (get-numbers-and-boards input)]
    (loop [numbers drawn-numbers
           boards boards]
      (let [drawn (first numbers)
            advanced-boards (mapv #(mark-number % drawn) boards)
            unfinished-boards (vec (remove board-complete advanced-boards))]
        (if (= 1 (count unfinished-boards))
          (play-until-end (drop 1 numbers) (nth unfinished-boards 0))
          (recur (drop 1 numbers) unfinished-boards))))))
