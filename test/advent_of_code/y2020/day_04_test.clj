(ns advent-of-code.y2020.day-04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-04 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 2]
      (is (= expected (part-1 (slurp (resource "2020/day-04-example.txt")))))))

  (testing "solution"
    (let [expected 226]
      (is (= expected (part-1 (slurp (resource "2020/day-04.txt"))))))))

(deftest part2
  (testing "example invalid"
    (let [expected 0]
      (is (= expected (part-2 (slurp (resource "2020/day-04-example-invalid.txt")))))))

  (testing "example valid"
    (let [expected 4]
      (is (= expected (part-2 (slurp (resource "2020/day-04-example.txt")))))))

  (testing "solution"
    (let [expected 160]
      (is (= expected (part-2 (slurp (resource "2020/day-04.txt"))))))))
