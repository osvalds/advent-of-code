(ns advent-of-code.y2021.day-16
  (:require [clojure.string :as str]))

(defn parse-int
  ([s] (Long/parseUnsignedLong s))
  ([s r] (Long/parseUnsignedLong s r)))

(def h->b {\0 "0000"
           \1 "0001"
           \2 "0010"
           \3 "0011"
           \4 "0100"
           \5 "0101"
           \6 "0110"
           \7 "0111"
           \8 "1000"
           \9 "1001"
           \A "1010"
           \B "1011"
           \C "1100"
           \D "1101"
           \E "1110"
           \F "1111"})

(defn hex->binary [hex-str]
  (apply str (map #(get h->b %) hex-str)))

(defn decode-number [number-part start-index]
  (loop [i 0
         result ""]
    (let [segment (subs number-part i (+ i 5))]
      (if (= \1 (first segment))
        (recur (+ i 5) (str result (subs segment 1 5)))
        [(parse-int (str result (subs segment 1 5)) 2) (+ start-index i 5)]))))

(defn decode [bin-str index]
  (let [version (parse-int (subs bin-str index (+ index 3)) 2)
        type-id (parse-int (subs bin-str (+ index 3) (+ index 6)) 2)]
    (if (= type-id 4)
      (let [[val next-start] (decode-number (subs bin-str (+ index 6)) (+ index 6))]
        [next-start
         {:version version
          :type-id type-id
          :value   val}])
      (let [length-type (get bin-str (+ index 6))]
        (if (= \0 length-type)
          (let [subpackets-length (parse-int (subs bin-str (+ index 7) (+ index 7 15)) 2)]
            [(+ index 7 15 subpackets-length)
             {:version    version
              :type-id    type-id
              :subpackets (loop [packets []
                                 i (+ index 7 15)]
                            (let [[next-start packet] (decode bin-str i)]
                              (if (= subpackets-length (- next-start (+ index 7 15)))
                                (conj packets packet)
                                (recur (conj packets packet) next-start))))}])

          (let [subpacket-count (parse-int (subs bin-str (+ index 7) (+ index 7 11)) 2)]
            (let [[next-index subpackets] (loop [packets []
                                                 iteration 0
                                                 i (+ index 7 11)]
                                            (if (< iteration subpacket-count)
                                              (let [[next-start packet] (decode bin-str i)]
                                                (recur (conj packets packet) (inc iteration) next-start))
                                              [i packets]))]
              [next-index
               {:version    version
                :type-id    type-id
                :subpackets subpackets}])))))))

(defn sum-versions [decoded-packets sum]
  (if (not (:subpackets decoded-packets))
    (+ sum (:version decoded-packets))
    (reduce
      (fn [result packet]
        (sum-versions packet result))
      (+ sum (:version decoded-packets))
      (:subpackets decoded-packets))))

(defn calculate [packet]
  (condp = (:type-id packet)
    0 (apply + (map calculate (:subpackets packet)))
    1 (apply * (map calculate (:subpackets packet)))
    2 (apply min (map calculate (:subpackets packet)))
    3 (apply max (map calculate (:subpackets packet)))
    4 (:value packet)
    5 (if (apply > (map calculate (:subpackets packet))) 1 0)
    6 (if (apply < (map calculate (:subpackets packet))) 1 0)
    7 (if (apply = (map calculate (:subpackets packet))) 1 0)))

(defn part-1
  "Day 16 Part 1"
  [input]
  (-> input (hex->binary) (decode 0) (second) (sum-versions 0)))

(defn part-2
  "Day 16 Part 2"
  [input]
  (-> input (hex->binary) (decode 0) (second) (calculate)))
