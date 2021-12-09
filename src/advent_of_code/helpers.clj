(ns advent-of-code.helpers)

(defn read-input
  [day]
  (slurp (clojure.java.io/resource day)))