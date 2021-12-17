(ns advent-of-code.y2021.day-17-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-17 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 45]
    (is (= expected (part-1 (slurp (resource "2021/day-17-example.txt"))))))

  (let [expected 25200]
    (is (= expected (part-1 (slurp (resource "2021/day-17.txt")))))))

(deftest part2

  (testing "intersects trench"
    (is (= true (intersects-trench? [7 2] [20 30] [-10 -5]))))

  (let [expected 112]
    (is (= expected (part-2 (slurp (resource "2021/day-17-example.txt"))))))

  (let [expected 3012]
    (is (= expected (part-2 (slurp (resource "2021/day-17.txt")))))))
