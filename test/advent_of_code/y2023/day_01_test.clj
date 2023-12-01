(ns advent-of-code.y2023.day-01-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2023.day-01 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 56506]
    (is (= expected (part-1 (slurp (resource "2023/day-01.txt")))))))

(deftest part2
  (let [expected 56017]
    (is (= expected (part-2 (slurp (resource "2023/day-01.txt")))))))
