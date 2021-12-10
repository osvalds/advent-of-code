(ns advent-of-code.y2021.day-10
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse [lines]
  (map #(str/split % #"") (str/split-lines lines)))

(def syntax-points
  {")" 3
   "]" 57
   "}" 1197
   ">" 25137})

(def completion-points
  {")" 1
   "]" 2
   "}" 3
   ">" 4})

(def close->open
  {")" "("
   "]" "["
   "}" "{"
   ">" "<"})

(def open->close (set/map-invert close->open))

(def close-brackets (set (keys close->open)))

(defn calc-syntax-error-points [line]
  (let [length (count line)]
    (loop [i 0
           stack '()]
      (let [current (get line i)
            closing-bracket? (contains? close-brackets current)
            is-valid? (or (not closing-bracket?) (= (first stack) (get close->open current)))
            next-stack (if (and is-valid? closing-bracket?) (drop 1 stack) (conj stack current))]
        (if (and is-valid? (< i length))
          (recur (inc i) next-stack)
          (if (and closing-bracket? (not is-valid?)) (get syntax-points current) 0))))))

(defn part-1
  "Day 11 Part 1"
  [input]
  (let [parsed (parse input)]
    (apply + (map calc-syntax-error-points parsed))))

(defn calc-score [completion]
  (reduce (fn [result closing-bracket]
            (+ (* result 5) (get completion-points closing-bracket))) 0 completion))

(defn autocomplete [line]
  (let [length (count line)]
    (loop [i 0
           stack '()]
      (let [current (get line i)
            closing-bracket? (contains? close-brackets current)
            is-valid? (or (not closing-bracket?) (= (first stack) (get close->open current)))
            next-stack (if (and is-valid? closing-bracket?) (drop 1 stack) (conj stack current))]
        (if (and is-valid? (< i length))
          (recur (inc i) next-stack)
          (rest (map #(get open->close %) next-stack)))))))

(defn part-2
  "Day 10 Part 2"
  [input]
  (let [parsed (parse input)
        incomplete-lines (filter #(= 0 (calc-syntax-error-points %)) parsed)
        middle (quot (count incomplete-lines) 2)]
    (nth (sort (map #(calc-score (autocomplete %)) incomplete-lines)) middle)))