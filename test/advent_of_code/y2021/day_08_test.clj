(ns advent-of-code.y2021.day-08-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-08 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 26]
      (is (= expected (part-1 (slurp (resource "2021/day-08-example.txt")))))))

  (testing "soļution"
    (let [expected 26]
      (is (= expected (part-1 (slurp (resource "2021/day-08.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 61229]
      (is (= expected (part-2 (slurp (resource "2021/day-08-example.txt")))))))

  (testing "soļution"
    (let [expected 61229]
      (is (= expected (part-2 (slurp (resource "2021/day-08.txt"))))))))
