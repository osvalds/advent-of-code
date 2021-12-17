(ns advent-of-code.y2021.day-17)

(defn parse [input]
  (->> input
    (re-seq #"-?[0-9]+")
    (map #(Integer/parseInt %))
    (partition 2)
    (mapv vec)))

(defn sum [n] (/ (* n (+ n 1)) 2))

(defn vx-range [[x-min x-max]]
  (loop [x 1]
    (if (<= x-min (sum x) x-max)
      (vec (range x (+ x-max 1)))
      (recur (inc x)))))

(defn vy-range [[y-min _]]
  (vec (range y-min (- y-min))))

(defn part-1
  "Day 17 Part 1"
  [input]
  (let [[[x-min x-max] [y-min y-max]] (parse input)]
    (sum (- (Math/abs ^int y-min) 1))))

(defn intersects-trench? [[vx-start vy-start] [x-min x-max] [y-min y-max]]
  (loop [x 0
         y 0
         vx vx-start
         vy vy-start]
    (let [new-x (+ x vx)
          new-y (+ y vy)]
      (if (or (> new-x x-max) (< new-y y-min))
        false
        (if (and (<= x-min new-x x-max) (<= y-min new-y y-max))
          true
          (recur new-x new-y (max 0 (dec vx)) (dec vy)))))))

(defn part-2
  "Day 17 Part 2"
  [input]
  (let [[[x-min x-max] [y-min y-max]] (parse input)
        vx-range (vx-range [x-min x-max])
        vy-range (vy-range [y-min y-max])
        all-start-velocities (for [x vx-range y vy-range] [x y])]
    (count (filter #(intersects-trench? % [x-min x-max] [y-min y-max]) all-start-velocities))))