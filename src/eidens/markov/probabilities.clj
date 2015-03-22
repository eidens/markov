(ns eidens.markov.probabilities
  (:require [eidens.markov.frequencies :as frequencies]))

(defn- transform-map
  [map func]
  (into {} (for [[k v] map] [k (func v)])))

(defn- calc-probabilities
  [frequencies]
  (let [total (reduce + 0 (vals frequencies))]
    (transform-map frequencies (partial * (/ 1 total)))))

(defn matrix
  ([frequency-matrix]
   (transform-map frequency-matrix calc-probabilities))
  ([order walk]
   (matrix (frequencies/matrix order walk))))
