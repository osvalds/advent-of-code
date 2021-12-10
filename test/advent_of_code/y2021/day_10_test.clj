(ns advent-of-code.y2021.day-10-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-10 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 26397]
      (is (= expected (part-1 (slurp (resource "2021/day-10-example.txt")))))))

  (testing "solution"
    (let [expected 215229]
      (is (= expected (part-1 (slurp (resource "2021/day-10.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 288957]
      (is (= expected (part-2 (slurp (resource "2021/day-10-example.txt")))))))

  (testing "soļution"
    (let [expected 1105996483]
      (is (= expected (part-2 (slurp (resource "2021/day-10.txt"))))))))
