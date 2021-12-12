(ns advent-of-code.y2020.day-03-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-03 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 7]
      (is (= expected (part-1 (slurp (resource "2020/day-03-example.txt")))))))

  (testing "solution"
    (let [expected 189]
      (is (= expected (part-1 (slurp (resource "2020/day-03.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 336]
      (is (= expected (part-2 (slurp (resource "2020/day-03-example.txt")))))))

  (testing "solution"
    (let [expected 1718180100]
      (is (= expected (part-2 (slurp (resource "2020/day-03.txt"))))))))
