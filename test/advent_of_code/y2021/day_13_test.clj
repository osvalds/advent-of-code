(ns advent-of-code.y2021.day-13-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-13 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "example"
    (let [expected 17]
      (is (= expected (part-1 (slurp (resource "2021/day-13-example.txt")))))))

  (testing "soļušon"
    (let [expected 675]
      (is (= expected (part-1 (slurp (resource "2021/day-13.txt"))))))))

(deftest part2
  (let [expected nil]
    (is (= expected (part-2 (slurp (resource "2021/day-13.txt")))))))
