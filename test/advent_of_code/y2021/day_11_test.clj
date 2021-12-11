(ns advent-of-code.y2021.day-11-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-11 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "neighbor coords"
    (let [expected [[-1 0] [-1 1] [0 1] [1 1] [1 0] [1 -1] [0 -1] [-1 -1]]]
      (is (= expected (neighbor-coords [0 0])))))

  (testing "example"
    (let [expected 1656]
      (is (= expected (part-1 (slurp (resource "2021/day-11-example.txt")))))))

  (testing "solution"
    (let [expected 1656]
      (is (= expected (part-1 (slurp (resource "2021/day-11.txt"))))))))

(deftest part2
  (let [expected 371]
    (is (= expected (part-2 (slurp (resource "2021/day-11.txt")))))))
