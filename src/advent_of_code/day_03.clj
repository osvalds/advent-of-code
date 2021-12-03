(ns advent-of-code.day-03
  (:require [clojure.string :as s]))


(defn get-bit-counter
  "Creates bit frequency counter

  101 => [[0,0] [0,0] [0,0]]

  First number will represent number of 0 bits (great for index access)
  Second number will represent number of 1 bits
  "
  [input]
  (vec (repeat (count input) [0 0])))

(defn update-freq-counter
  "[[0,0] [0,0] [0,0]] 101 => [[0 1] [1 0] [0 1]]"
  [counters line]
  (vec (map-indexed (fn [idx itm]
                      (let [num (Character/digit ^char itm 10)
                            counter-tuple (get counters idx)]
                        (update counter-tuple num inc))) line)))

(defn get-bit-char [a b comp-fn]
  (if (comp-fn a b) \0 \1))

(defn get-gamma-bit [[a b]]
  (get-bit-char a b <))

(defn get-sigma-bit [[a b]]
  (get-bit-char a b >))

(defn get-gamma [freq-count]
  (Integer/parseInt (s/join (map get-gamma-bit freq-count)) 2))

(defn get-sigma [freq-count]
  (Integer/parseInt (s/join (map get-sigma-bit freq-count)) 2))

(defn get-bit-freq [input]
  (let [bit-counter (get-bit-counter (first input))]
    (reduce update-freq-counter bit-counter input)))

(defn part-1
  "Day 03 Part 1"
  [input]
  (let [parsed-input (s/split-lines input)
        bit-freq (get-bit-freq parsed-input)
        gamma (get-gamma bit-freq)
        sigma (get-sigma bit-freq)]
    (* gamma sigma)))

(defn filter-remaining [inputs idx comp fallback]
  (let [freqs (get-bit-freq inputs)
        [a b] (get freqs idx)
        bit-char (if (= a b) fallback (get-bit-char a b comp))]
    (filter #(= (nth % idx) bit-char) inputs)))

;; TODO this should spark more joy
(defn get-o2-rating [inputs]
  (loop [i 0
         remaining (filter-remaining inputs i > \1)]
    (if (= (count remaining) 1)
      (first remaining)
      (recur (inc i) (filter-remaining remaining (inc i) > \1)))))

;; TODO this should spark more joy
(defn get-co2-rating [inputs]
  (loop [i 0
         remaining (filter-remaining inputs i < \0)]
    (if (= (count remaining) 1)
      (first remaining)
      (recur (inc i) (filter-remaining remaining (inc i) < \0)))))

(defn part-2
  "Day 03 Part 2"
  [input]
  (let [parsed-input (s/split-lines input)
        co2-rating (Integer/parseInt (get-co2-rating parsed-input) 2)
        o2-rating (Integer/parseInt (get-o2-rating parsed-input) 2)]
    (* co2-rating o2-rating)))
