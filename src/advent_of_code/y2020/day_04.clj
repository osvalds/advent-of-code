(ns advent-of-code.y2020.day-04
  (:require [clojure.string :as str]))

(defn create-record [arr]
  (into {} (map #(str/split % #":") arr)))

(defn parse [input]
  (let [grouped (map #(str/split % #"\n|\s") (str/split input #"\n\n"))]
    (map create-record grouped)))

(defn part-1
  "Day 04 Part 1"
  [input]
  (let [parsed (parse input)]
    (->> parsed
      (filter #(or
                 (= (count (keys %)) 8)
                 (and (= (count (keys %)) 7) (not (get % "cid")))))
      (count))))

(defn valid-byr? [passport]
  (< 1919 (Integer/parseInt (get passport "byr" "0")) 2003))

(defn valid-iyr? [passport]
  (< 2009 (Integer/parseInt (get passport "iyr" "0")) 2021))

(defn valid-eyr? [passport]
  (< 2019 (Integer/parseInt (get passport "eyr" "0")) 2031))

(defn valid-hgt? [passport]
  (let [hgt (get passport "hgt" "0in")]
    (cond
      (str/ends-with? hgt "in") (< 58 (Integer/parseInt (str/replace hgt #"in" "")) 76)
      (str/ends-with? hgt "cm") (< 149 (Integer/parseInt (str/replace hgt #"cm" "")) 194)
      :else false)))

(defn valid-hcl? [passport]
  (re-matches #"\#[a-f0-9]{6}" (get passport "hcl" "")))

(defn valid-ecl? [passport]
  (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} (get passport "ecl" "")))

(defn valid-pid? [passport]
  (re-matches #"[0-9]{9}" (get passport "pid" "")))

(defn valid-passport? [passport]
  (and (valid-byr? passport)
    (valid-iyr? passport)
    (valid-eyr? passport)
    (valid-hgt? passport)
    (valid-hcl? passport)
    (valid-ecl? passport)
    (valid-pid? passport)))

(defn part-2
  "Day 04 Part 2"
  [input]
  (let [parsed (parse input)]
    (->> parsed
      (filter valid-passport?)
      (count))))


