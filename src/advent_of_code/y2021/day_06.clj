(ns advent-of-code.y2021.day-06
  (:require [clojure.string :as s]))

(defn parse [input] (mapv #(Integer/parseInt %) (s/split input #",")))

(defn count-zeros [fishes]
  (->> fishes (filter #(= 0 %)) count))

(defn iterate-existing [fishes]
  (->> fishes (mapv #(if (= % 0) 6 (dec %)))))

(defn part-1
  "Day 06 Part 1"
  [input total-days]
  (let [parsed (parse input)]
    (loop [current-day 0
           fishes parsed]
      (if (< current-day total-days)
        (let [newborns (count-zeros fishes)
              next-gen (iterate-existing fishes)
              next-gen-full (vec (concat next-gen (vec (repeat newborns 8))))]
          (recur (inc current-day) next-gen-full))
        (count fishes)))))

(defn init-counters [input]
  (let [fishes (parse input)
        start (vec (repeat 9 0))]
    (reduce (fn [result fish]
              (update result fish inc)) start fishes)))

(defn part-2
  "Day 06 Part 2"
  [input total-days]
  (let [counters (init-counters input)]
    (loop [day 0
           fish-counters counters]
      (if (< day total-days)
        (let [zero-day (first fish-counters)
              rest-days (vec (rest fish-counters))
              next-counters (update (conj rest-days zero-day) 6 #(+ % zero-day))]
          (recur (inc day) next-counters))
        (apply + fish-counters)))))
