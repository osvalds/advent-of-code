(ns advent-of-code.y2021.day-14-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-14 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 1588]
      (is (= expected (part-1 (slurp (resource "2021/day-14-example.txt")))))))

  (testing "soļution"
    (let [expected 2345]
      (is (= expected (part-1 (slurp (resource "2021/day-14.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 2188189693529]
      (is (= expected (part-2 (slurp (resource "2021/day-14-example.txt")))))))

  (testing "soļution"
    (let [expected nil]
      (is (= expected (part-2 (slurp (resource "2021/day-14.txt"))))))))
