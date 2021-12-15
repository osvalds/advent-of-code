(ns advent-of-code.y2020.day-06
  (:require [clojure.string :as str]))

(defn unique-answers [group]
  (into #{} (str/split (str/replace group #"\n" "") #"")))

(defn parse [input parser]
  (map parser (str/split input #"\n\n")))

(defn part-1
  "Day 04 Part 1"
  [input]
  (let [parsed (parse input unique-answers)]
    (apply + (map count parsed))))

(defn parse-group-p2 [group]
  (let [uniq-answers (unique-answers group)
        all-answers (str/split-lines group)]
    (filter (fn [needle] (every? #(str/includes? % needle) all-answers)) uniq-answers)))

(defn part-2
  "Day 04 Part 2"
  [input]
  (let [parsed (parse input parse-group-p2)]
    (apply + (map count parsed))))


