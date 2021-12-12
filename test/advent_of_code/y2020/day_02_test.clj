(ns advent-of-code.y2020.day-02-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-02 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 2]
      (is (= expected (part-1 (slurp (resource "2020/day-02-example.txt")))))))

  (testing "solution"
    (let [expected 580]
      (is (= expected (part-1 (slurp (resource "2020/day-02.txt"))))))))


(deftest part2
  (testing "example"
    (let [expected 1]
      (is (= expected (part-2 (slurp (resource "2020/day-02-example.txt")))))))

  (testing "solution"
    (let [expected 611]
      (is (= expected (part-2 (slurp (resource "2020/day-02.txt"))))))))
