(ns advent-of-code.y2021.day-04-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-04 :refer :all]
            [clojure.java.io :refer [resource]]))

(def sample-board [[22 13 17 11 0] [8 2 23 4 24] [21 9 14 16 7] [6 10 3 18 5] [1 12 20 15 19]])
(def row-complete [[22 13 17 11 0] [8 2 23 4 24] [nil nil nil nil nil] [6 10 3 18 5] [1 12 20 15 19]])
(def col-complete [[22 8 21 6 nil] [13 2 9 10 nil] [17 23 14 3 nil] [11 4 16 18 nil] [0 24 7 5 nil]])


(deftest part1
  (testing "drawn numbers"
    (let [input "7,4,9,5,11,17,23,2,0,14"
          expected [7 4 9 5 11 17 23 2 0 14]]
      (is (= expected (parse-int-line input #","))))

    (let [input " 3 15 69  5 23"
          expected [3 15 69 5 23]]
      (is (= expected (parse-int-line input #" +")))))

  (testing "draw-number"
    (let [input [[22 13 17 11 0] [8 2 23 4 24] [21 9 14 16 7] [6 10 3 18 5] [1 12 20 15 19]]
          expected [[22 13 17 11 0] [8 2 23 4 24] [21 9 14 16 7] [6 10 3 18 5] [1 12 20 nil 19]]]
      (is (= expected (mark-number input 15)))))

  (testing "board-complete?"
    (is (= nil (board-complete sample-board))))

  (testing "board-complete? (row)"
    (is (= row-complete (board-complete row-complete))))

  (testing "board-complete? (col)"
    (is (= col-complete (board-complete col-complete))))

  (testing "example solution"
    (let [expected 4512]
      (is (= expected (part-1 (slurp (resource "2021/day-04-example.txt")))))))

  (testing "solution p1"
    (let [expected 25410]
      (is (= expected (part-1 (slurp (resource "2021/day-04.txt"))))))))

(deftest part2
  (testing "example"
    (let [expected 1924]
      (is (= expected (part-2 (slurp
                                (resource "2021/day-04-example.txt")))))))
  (testing "solution"
    (let [expected 2730]
      (is (= expected (part-2 (slurp (resource "2021/day-04.txt"))))))))
