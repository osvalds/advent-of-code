(ns advent-of-code.y2020.day-1-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-01 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 158916]
    (is (= expected (part-1 (slurp (resource "2020/day-01.txt")))))))

(deftest part2
  (let [expected 165795564]
    (is (= expected (part-2 (slurp (resource "2020/day-01.txt")))))))
