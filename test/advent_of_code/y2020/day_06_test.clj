(ns advent-of-code.y2020.day-06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-06 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 11]
      (is (= expected (part-1 (slurp (resource "2020/day-06-example.txt")))))))

  (testing "solution"
    (let [expected 6416]
      (is (= expected (part-1 (slurp (resource "2020/day-06.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 6]
      (is (= expected (part-2 (slurp (resource "2020/day-06-example.txt")))))))

  (testing "solution"
    (let [expected 3050]
      (is (= expected (part-2 (slurp (resource "2020/day-06.txt"))))))))
