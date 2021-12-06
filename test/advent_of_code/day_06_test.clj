(ns advent-of-code.day-06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-06 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1

  (testing "example day 18"
    (let [expected 26]
      (is (= expected (part-1 (slurp (resource "day-06-example.txt")) 18)))))

  (testing "example day 80"
    (let [expected 5934]
      (is (= expected (part-1 (slurp (resource "day-06-example.txt")) 80)))))


  (testing "soļušion day 80"
    (let [expected 5934]
      (is (= expected (part-1 (slurp (resource "day-06.txt")) 80))))))


(deftest part2

  (testing "exmaple"
    (let [expected 26]
      (is (= expected (part-2 (slurp (resource "day-06-example.txt")) 18)))))

  (testing "exmaple"
    (let [expected 5934]
      (is (= expected (part-2 (slurp (resource "day-06-example.txt")) 80)))))

  (testing "example pt2"
    (let [expected 26984457539]
      (is (= expected (part-2 (slurp (resource "day-06-example.txt")) 256)))))

  (testing "example pt2"
    (let [expected 1644286074024]
      (is (= expected (part-2 (slurp (resource "day-06.txt")) 256))))))
