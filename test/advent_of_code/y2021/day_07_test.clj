(ns advent-of-code.y2021.day-07-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-07 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 37]
    (is (= expected (part-1 (slurp (resource "2021/day-07.txt")))))))

(deftest part2
  (let [expected 98368490]
    (is (= expected (part-2 (slurp (resource "2021/day-07.txt")))))))
