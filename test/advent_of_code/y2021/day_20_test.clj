(ns advent-of-code.y2021.day-20-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-20 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 35]
    (is (= expected (part-1 (slurp (resource "2021/day-20-example.txt"))))))

  (let [expected 5432]
    (is (= expected (part-1 (slurp (resource "2021/day-20.txt")))))))


(deftest part2
  (let [expected 3351]
    (is (= expected (part-2 (slurp (resource "2021/day-20-example.txt"))))))

  (let [expected 16016]
    (is (= expected (part-2 (slurp (resource "2021/day-20.txt")))))))
