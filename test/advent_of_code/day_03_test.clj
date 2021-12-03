(ns advent-of-code.day-03-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-03 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1

  (testing "bit counter Maker (blessed be thy)"
    (let [expected [[0 0] [0 0] [0 0]]]
      (is (= expected (get-bit-counter "101")))))

  (testing "update counters"
    (let [line "101"
          counters (get-bit-counter line)
          expected [[0 1] [1 0] [0 1]]]
      (is (= expected (update-freq-counter counters line)))))

  (testing "gamma (most common) bit getter"
    (is (= \0 (get-gamma-bit [2 4])))
    (is (= \1 (get-gamma-bit [4 2]))))

  (testing "example"
    (let [expected 198]
      (is (= expected (part-1 (slurp (resource "day-03-example.txt")))))))

  (testing "solution"
    (let [expected 3985686]
      (is (= expected (part-1 (slurp (resource "day-03.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 230]
      (is (= expected (part-2 (slurp (resource "day-03-example.txt")))))))

  (testing "solution"
    (let [expected 2555739]
      (is (= expected (part-2 (slurp (resource "day-03.txt"))))))))
