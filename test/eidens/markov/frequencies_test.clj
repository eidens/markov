(ns eidens.markov.frequencies-test
  (:require [clojure.test :refer :all]
            [eidens.markov.frequencies]))

(def becomes "->")
(defn- build-msg [msg coll matrix]
  (str msg coll becomes matrix))

(deftest frequencies-matrix-test
  (testing "eidens.markov.frequencies/matrix"
    (let [coll '(0 1 0),
          matrix {'(0) {'(1) 1}
                  '(1) {'(0) 1}}]
      (is (= matrix (eidens.markov.frequencies/matrix 1 coll))
          (build-msg "generates a frequency matrix from an collection"
                     coll matrix)))
    (let [coll '(0 1 0 2 1 1 2 3),
          matrix {'(0) {'(1) 1, '(2) 1},
                  '(1) {'(0) 1, '(1) 1, '(2) 1},
                  '(2) {'(1) 1, '(3) 1}}
          matrix-2nd-order {'(0 1) {'(0) 1}, '(1 0) {'(2) 1},
                            '(0 2) {'(1) 1}, '(2 1) {'(1) 1},
                            '(1 1) {'(2) 1}, '(1 2) {'(3) 1}}]
      (is (= matrix (eidens.markov.frequencies/matrix 1 coll))
          (build-msg "a frequency matrix is a map: keys are a list of the previous state, values another map: from a list of the next state to how often it occured"
                     coll matrix))
      (is (= matrix-2nd-order (eidens.markov.frequencies/matrix 2 coll))
          (build-msg "accepts an order: how many items of the input collection make up the previous state"
                     coll matrix-2nd-order)))
    (let [walk '((0 0) {1 1} (0 0) 1)
          matrix {'((0 0)) {'({1 1}) 1, '(1) 1}
                  '({1 1}) {'((0 0)) 1}}]
      (is (= matrix (eidens.markov.frequencies/matrix 1 walk))
          "the given coll must be a Clojure collection, but its items can be of any type, or of different types"))))
