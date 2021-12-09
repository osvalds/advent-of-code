(ns advent-of-code.y2021.day-08
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-line [line]
  (mapv #(str/split % #"\s") (str/split line #"\s\|\s")))

(defn parse [input]
  (let [lines (str/split-lines input)]
    (mapv parse-line lines)))

(defn count-1478 [out]
  (->>
    (map count out)
    (filter #(contains? #{2 3 4 7} %))
    count))

(defn part-1
  "Day 08 Part 1"
  [input]
  (let [parsed (parse input)]
    (reduce
      (fn [result [_ out]]
        (+ result (count-1478 out)))
      0
      parsed)))

(defn find-by-intersection-size [seq query size]
  (first (filter #(= size (count (set/intersection (set %) (set query)))) seq)))

(defn three [two-three-five one]
  (find-by-intersection-size two-three-five one 2))

(defn nine [zero-six-nine four]
  (find-by-intersection-size zero-six-nine four 4))

(defn zero [zero-six seven]
  (find-by-intersection-size zero-six seven 3))

(defn two-and-five [two-three-five three four]
  (let [two-five (remove #(= % three) two-three-five)]
    (if (= 2 (count (set/intersection
                      (set (first two-five))
                      (set four))))
      two-five
      (-> two-five reverse vec))))

(defn get-line-key [input]
  (let [digits-sorted (mapv #(->> % sort (apply str)) (sort-by count input))
        one (get digits-sorted 0)
        seven (get digits-sorted 1)
        four (get digits-sorted 2)
        two-three-five (subvec digits-sorted 3 6)
        three (three two-three-five one)
        [two five] (two-and-five two-three-five three four)
        zero-six-nine (subvec digits-sorted 6 9)
        nine (nine zero-six-nine four)
        zero-six (remove #(= % nine) zero-six-nine)
        zero (zero zero-six seven)
        six (first (remove #(= % zero) zero-six))
        eight (last digits-sorted)]
    {zero  0
     one   1
     two   2
     three 3
     four  4
     five  5
     six   6
     seven 7
     eight 8
     nine  9}))

(defn part-2
  "Day 08 Part 2"
  [input]
  (let [parsed (parse input)]
    (apply + (map
               (fn [[k reading]]
                 (let [line-key (get-line-key k)
                       reading-sorted (mapv #(->> % sort (apply str)) reading)]
                   (Integer/parseInt (apply str (map #(get line-key %) reading-sorted)))))
               parsed))))