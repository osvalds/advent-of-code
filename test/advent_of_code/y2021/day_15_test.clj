(ns advent-of-code.y2021.day-15-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-15 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 40]
      (is (= expected (part-1 (slurp (resource "2021/day-15-example.txt")))))))

  (testing "solution"
    (let [expected 527]
      (is (= expected (part-1 (slurp (resource "2021/day-15.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 315]
      (is (= expected (part-2 (slurp (resource "2021/day-15-example.txt")))))))

  (testing "example"
    (let [expected 2887]
      (is (= expected (part-2 (slurp (resource "2021/day-15.txt"))))))))
