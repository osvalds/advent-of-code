(ns advent-of-code.y2021.day-21
  (:require [clojure.string :as str]))

(defn part-1
  "Day 21 Part 1"
  [p1-start p2-start]
  (loop [i 0
         p1-pos p1-start
         p2-pos p2-start
         p1-curr-sum 0
         p2-curr-sum 0]
    (let [p1-roll (apply + (map #(inc (rem (dec (+ % (* i 6))) 100)) (range 1 4)))
          p2-roll (apply + (map #(inc (rem (dec (+ % (* i 6))) 100)) (range 4 7)))
          p1-new (inc (rem (dec (+ p1-pos p1-roll)) 10))
          p2-new (inc (rem (dec (+ p2-pos p2-roll)) 10))
          p1-new-sum (+ p1-curr-sum p1-new)
          p2-new-sum (+ p2-curr-sum p2-new)]
      (if (<= 1000 (max p1-new-sum p2-new-sum))
        (*
          (if (> p1-new-sum p2-new-sum) p2-curr-sum p1-new-sum)
          (if (> p1-new-sum p2-new-sum) (+ 3 (* i 6)) (+ 6 (* i 6))))
        (recur (inc i) p1-new p2-new p1-new-sum p2-new-sum)))))

(defn wrap-10 [x] (inc (mod (dec x) 10)))


;; taken from reddit
(def quantum
  (memoize
    (fn
      ([p1 p2] (quantum p1 p2 0 0))
      ([p1 p2 sc1 sc2]
       (if (<= 21 sc2)
         [0 1]
         (reduce
           #(mapv + %1 %2)
           (for [r1 [1 2 3] r2 [1 2 3] r3 [1 2 3]
                 :let [p1 (wrap-10 (+ p1 r1 r2 r3))
                       sc1 (+ sc1 p1)]]
            (reverse (quantum p2 p1 sc2 sc1)))))))))

(defn part-2
  "Day 21 Part 2"
  [p1-start p2-start]
  (quantum p1-start p2-start))


