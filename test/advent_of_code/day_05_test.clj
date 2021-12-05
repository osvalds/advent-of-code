(ns advent-of-code.day-05-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-05 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1

  (testing "line-parsing"
    (let [input "3,4 -> 1,4"
          expected [[3 4] [1 4]]]
      (is (= expected (parse-line input)))))

  (testing "is horizontal"
    (let [input [[3 4] [1 4]]]
      (is (= true (is-hov? input)))))

  (testing "is horizontal"
    (let [input [[4 1] [4 1]]]
      (is (= true (is-hov? input)))))

  (testing "verstappen"
    (let [input [[1 4] [5 1]]
          max [0 0]
          result [5 4]]
      (is (= result (max-coord input max)))))

  (testing "vec2d"
    (let [result [[0 0 0] [0 0 0] [0 0 0]]]
      (is (= result (vec2d 3 3 0)))))

  (testing "update-h-line"
    (let [input [0 0 0 0 0]
          result [0 1 1 1 0]]
      (is (= result (update-hline input 1 3)))))

  (testing "sorting"
    (let [input [[9 4] [3 4]]]
      (is (= [[3 4] [9 4]] (sort-coord input)))))

  (testing "example solution"
    (let [expected 5]
      (is (= expected (part-1 (slurp (resource "day-05-example.txt")))))))

  (testing "solution p1"
    (let [expected 5197]
      (is (= expected (part-1 (slurp (resource "day-05.txt"))))))))

(deftest part2
  (testing "[[8 0] [0 8]]"
    (let [empty-start (vec2d 10 10 0)
          line [[8 0] [0 8]]
          result '([0 0 0 0 0 0 0 0 1 0]
                   [0 0 0 0 0 0 0 1 0 0]
                   [0 0 0 0 0 0 1 0 0 0]
                   [0 0 0 0 0 1 0 0 0 0]
                   [0 0 0 0 1 0 0 0 0 0]
                   [0 0 0 1 0 0 0 0 0 0]
                   [0 0 1 0 0 0 0 0 0 0]
                   [0 1 0 0 0 0 0 0 0 0]
                   [1 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0])]
      (is (= result (update-diag empty-start line)))))

  (testing "[[0 0] [8 8]]"
    (let [empty-start (vec2d 10 10 0)
          line [[0 0] [8 8]]
          result '([1 0 0 0 0 0 0 0 0 0]
                   [0 1 0 0 0 0 0 0 0 0]
                   [0 0 1 0 0 0 0 0 0 0]
                   [0 0 0 1 0 0 0 0 0 0]
                   [0 0 0 0 1 0 0 0 0 0]
                   [0 0 0 0 0 1 0 0 0 0]
                   [0 0 0 0 0 0 1 0 0 0]
                   [0 0 0 0 0 0 0 1 0 0]
                   [0 0 0 0 0 0 0 0 1 0]
                   [0 0 0 0 0 0 0 0 0 0])]
      (is (= result (update-diag empty-start line)))))

  (testing "[[5 5] [8 2]]"
    (let [empty-start (vec2d 10 10 0)
          line [[5 5] [8 2]]
          result '([0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 1 0]
                   [0 0 0 0 0 0 0 1 0 0]
                   [0 0 0 0 0 0 1 0 0 0]
                   [0 0 0 0 0 1 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0])]
      (is (= result (update-diag empty-start line)))))

  (testing "[[6 4] [2 0]]"
    (let [empty-start (vec2d 10 10 0)
          line [[6 4] [2 0]]
          result '([0 0 1 0 0 0 0 0 0 0]
                   [0 0 0 1 0 0 0 0 0 0]
                   [0 0 0 0 1 0 0 0 0 0]
                   [0 0 0 0 0 1 0 0 0 0]
                   [0 0 0 0 0 0 1 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0]
                   [0 0 0 0 0 0 0 0 0 0])]
      (is (= result (update-diag empty-start line)))))

  (testing "is-diag?"
    (is (= true (is-diag? [[1 1] [3 3]])))
    (is (= false (is-diag? [[2 2] [2 1]]))))

  (testing "example"
    (let [expected 12]
      (is (= expected (part-2 (slurp (resource "day-05-example.txt")))))))

  (let [expected 18605]
    (is (= expected (part-2 (slurp (resource "day-05.txt")))))))
