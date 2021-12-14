(ns advent-of-code.y2021.day-14
  (:require [clojure.string :as str]))

(defn parse-rule [rule]
  (let [[key insert] (str/split rule #" -> ")]
    {key [(str (first key) insert) (str insert (second key))]}))

(defn empty-counters [rules]
  (reduce
    (fn [result rule]
      (assoc result (key rule) 0))
    {}
    rules))

(defn parse [input]
  (let [[template r] (str/split input #"\n\n")
        rules (into {} (mapv parse-rule (str/split-lines r)))]
    [template rules (empty-counters rules)]))

(defn insert [counters rules]
  (reduce
    (fn [result [rule-key count]]
      (if (= count 0)
        result
        (let [[deriv1 deriv2] (get rules rule-key)]
          (-> result
            (update deriv1 + count)
            (update deriv2 + count)))))
    (empty-counters rules)
    counters))

(defn sum [old val]
  (if old (+ old val) val))

(defn freq-diff [counters template]
  (let [fchar (first template)
        char-counts (->> counters
                      (reduce (fn [result [k v]]
                                (update result (last k) sum v)) {fchar 1})
                      (vals)
                      (sort))]
    (- (last char-counts) (first char-counts))))

(defn init-counters [template counters]
  (->> template
    (partition 2 1)
    (map str/join)
    (reduce (fn [result pair]
              (update result pair sum 1)) counters)))

(defn solve [input iterations]
  (let [[template rules counters] (parse input)]
    (loop [i 0
           loop-counters (init-counters template counters)]
      (if (< i iterations)
        (recur (inc i) (insert loop-counters rules))
        (freq-diff loop-counters template)))))

(defn part-1
  "Day 14 Part 1"
  [input]
  (solve input 10))

(defn part-2
  "Day 14 Part 2"
  [input]
  (solve input 40))
