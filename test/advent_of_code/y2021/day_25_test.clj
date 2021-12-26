(ns advent-of-code.y2021.day-25-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-25 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 58]
    (is (= expected (part-1 (slurp (resource "2021/day-25-example.txt"))))))

  (let [expected 509]
    (is (= expected (part-1 (slurp (resource "2021/day-25.txt")))))))

(deftest part2
  (let [expected nil]
    (is (= expected (part-2 (slurp (resource "2021/day-25.txt")))))))
