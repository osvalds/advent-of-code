(ns advent-of-code.y2020.day-02
  (:require [clojure.string :as str]))

(defn parse-line [line]
  (let [[min-max search password] (str/split line #"\s")]
    [(mapv #(Integer/parseInt %) (str/split min-max #"-"))
     (first search)
     password]))

(defn part-1
  "Day 25 Part 1"
  [input]
  (let [parsed (map parse-line (str/split-lines input))]
    (->> parsed
      (filter
        (fn [[[min max] letter password]]
          (<= min (get (frequencies password) letter 0) max)))
      (count))))

(defn part-2
  "Day 25 Part 2"
  [input]
  (let [parsed (map parse-line (str/split-lines input))]
    (->> parsed
      (filter
        (fn [[[pos1 pos2] letter password]]
          (let [first (get password (dec pos1) -1)
                second (get password (dec pos2) -1)]
            (or
              (and (not (= letter first)) (= letter second))
              (and (= letter first) (not (= letter second)))))))
      (count))))
