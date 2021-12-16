(ns advent-of-code.y2021.day-16-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.y2021.day-16 :refer :all]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (testing "hex str to binary string"
    (let [input "38006F45291200"
          expected "00111000000000000110111101000101001010010001001000000000"]
      (is (= expected (hex->binary input))))

    (let [input "EE00D40C823060"
          expected "11101110000000001101010000001100100000100011000001100000"]
      (is (= expected (hex->binary input))))

    (let [input "D2FE28"
          expected "110100101111111000101000"]
      (is (= expected (hex->binary input)))))

  (testing "8A004A801A8002F478"
    (let [input (hex->binary "8A004A801A8002F478")
          expected 16]
      (is (= expected (sum-versions (second (decode input 0)) 0)))))

  (testing "620080001611562C8802118E34"
    (let [input (hex->binary "620080001611562C8802118E34")
          expected 12]
      (is (= expected (sum-versions (second (decode input 0)) 0)))))

  (testing "C0015000016115A2E0802F182340"
    (let [input (hex->binary "C0015000016115A2E0802F182340")
          expected 23]
      (is (= expected (sum-versions (second (decode input 0)) 0)))))

  (testing "A0016C880162017C3686B18A3D4780"
    (let [input (hex->binary "A0016C880162017C3686B18A3D4780")
          expected 31]
      (is (= expected (sum-versions (second (decode input 0)) 0)))))

  (testing "011100101011110110000000110001010000"
    (is (= 30800350288
          (parse-int "011100101011110110000000110001010000" 2))))

  (let [expected 986]
    (is (= expected (part-1 (slurp (resource "2021/day-16.txt")))))))

(deftest part2
  (let [expected 3
        result (part-2 "C200B40A82")]
    (is (= expected (part-2 "C200B40A82"))))

  (let [expected 54]
    (is (= expected (part-2 "04005AC33890"))))

  (let [expected 18234816469452]
    (is (= expected (part-2 (slurp (resource "2021/day-16.txt")))))))
