(ns advent-of-code.y2021.day-12
  (:require [clojure.string :as str])
  (:import (clojure.lang PersistentQueue)))

(defn connected-vertices [edges vertex]
  (reduce
    (fn [result [A B]]
      (if (or (= A vertex) (= B vertex))
        (conj result (if (= A vertex) B A))
        result))
    []
    edges))

(defn input->graph [input]
  (let [edges (map #(str/split % #"-") (str/split-lines input))
        vertices (into #{} (flatten edges))]
    (reduce
      (fn [graph vertex]
        (assoc graph vertex (connected-vertices edges vertex)))
      {}
      vertices)))

(defn can-add-p1? [current-path node]
  (or (every? #(Character/isUpperCase ^char %) node)
    (not (some #(= node %) current-path))))

(defn add-new-paths-to-queue [next-q graph current-path can-add?]
  (let [last-node (last current-path)
        adj-nodes (filter #(can-add? current-path %) (get graph last-node))
        new-paths (mapv #(conj current-path %) adj-nodes)]
    (into next-q new-paths)))

(defn process-q [graph q paths end can-add?]
  (let [current-path (peek q)
        next-q (pop q)]
    (if (= end (last current-path))
      [(conj paths current-path) next-q]
      [paths (add-new-paths-to-queue next-q graph current-path can-add?)])))

(defn all-paths [graph start end can-add?]
  (loop [q (-> PersistentQueue/EMPTY
             (conj [start]))
         paths []]
    (if (empty? q)
      paths
      (let [[next-paths next-q] (process-q graph q paths end can-add?)]
        (recur next-q next-paths)))))

(defn part-1
  "Day 12 Part 1"
  [input]
  (let [graph (input->graph input)]
    (count (all-paths graph "start" "end" can-add-p1?))))

(defn can-add-p2? [can-be-twice current-path node]
  (let [can-add? (and (= node can-be-twice) (< (count (filter #(= % node) current-path)) 2))
        result (or
                 (every? #(Character/isUpperCase ^char %) node)
                 (or
                   can-add?
                   (not (some #(= node %) current-path))))]
    result))

(defn get-smalls [graph]
  (filter
    (fn [node]
      (and (not (or (= "end" node) (= "start" node)))
        (every? #(Character/isLowerCase ^char %) node)))
    (keys graph)))

(defn part-2
  "Day 12 Part 2"
  [input]
  (let [graph (input->graph input)]
    (->> graph
      (get-smalls)
      (map #(all-paths graph "start" "end" (partial can-add-p2? %)))
      (apply concat)
      (into #{})
      (count))))
