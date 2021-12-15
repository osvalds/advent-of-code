(ns advent-of-code.y2020.day-05-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2020.day-05 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "set-id"
    (let [ss (parse-row "BFFFBBFRRR")
          id 567]
      (is (= id (seat-id ss)))))

  (testing "solution"
    (let [expected 828]
      (is (= expected (part-1 (slurp (resource "2020/day-05.txt"))))))))

(deftest part2
  (testing "solution"
    (let [expected 565]
      (is (= expected (part-2 (slurp (resource "2020/day-05.txt"))))))))
