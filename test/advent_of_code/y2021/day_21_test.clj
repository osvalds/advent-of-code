(ns advent-of-code.y2021.day-21-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-21 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 739785]
    (is (= expected (part-1 4 8))))

  (let [expected 989352]
    (is (= expected (part-1 5 9)))))

(deftest part2
  (let [expected 444356092776315]
    (is (= expected (part-2 4 8))))

  (let [expected 430229563871565]
    (is (= expected (part-2 5 9)))))
