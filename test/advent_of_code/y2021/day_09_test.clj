(ns advent-of-code.y2021.day-09-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-09 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 15]
      (is (= expected (part-1 (slurp (resource "2021/day-09-example.txt")))))))

  (testing "solution"
    (let [expected 452]
      (is (= expected (part-1 (slurp (resource "2021/day-09.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 1134]
      (is (= expected (part-2 (slurp (resource "2021/day-09-example.txt")))))))

  (testing "solution"
    (let [expected 1263735]
      (is (= expected (part-2 (slurp (resource "2021/day-09.txt"))))))))
