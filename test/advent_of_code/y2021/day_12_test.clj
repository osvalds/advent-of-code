(ns advent-of-code.y2021.day-12-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-12 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "can add part1"
    (let [path ["start" "A" "b" "A" "c"]]
      (is (= true (can-add-p1? path "end")))
      (is (= true (can-add-p1? path "XYZ")))
      (is (= false (can-add-p1? path "b")))
      (is (= false (can-add-p1? path "start")))
      (is (= true (can-add-p1? path "A")))))

  (testing "basic"
    (let [expected 10]
      (is (= expected (part-1 (slurp (resource "2021/day-12-example.txt")))))))

  (testing "larger"
    (let [expected 19]
      (is (= expected (part-1 (slurp (resource "2021/day-12-example-larger.txt")))))))

  (testing "even larger"
    (let [expected 226]
      (is (= expected (part-1 (slurp (resource "2021/day-12-example-even-larger.txt")))))))

  (testing "solution"
    (let [expected 5157]
      (is (= expected (part-1 (slurp (resource "2021/day-12.txt"))))))))


(deftest part2
  (testing "can-add-part2"
    (let [path ["start" "A" "b" "A" "c"]]
      (is (= false (can-add-p2? "b" path "start")))
      (is (= true (can-add-p2? "b" path "b")))
      (is (= true (can-add-p2? "b" path "A")))
      (is (= false (can-add-p2? "b" path "c")))
      (is (= true (can-add-p2? "b" path "end"))))

    (let [path ["start" "A" "b" "A" "c" "end"]]
      (is (= false (can-add-p2? "b" path "end")))))

  (testing "example"
    (let [expected 36]
      (is (= expected (part-2 (slurp (resource "2021/day-12-example.txt")))))))

  (testing "solution"
    (let [expected 144309]
      (is (= expected (part-2 (slurp (resource "2021/day-12.txt"))))))))
