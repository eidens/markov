# markov

A simple Clojure library for the generation of markov matrices.
Accepts Clojure collections with items of any or different types and
produces a frequency or probability matrix.

## Usage

```` Clojure
(eidens.markov.frequencies/matrix 1 '(0 1 0))
=> {'(0) {'(1) 1},
    '(1) {'(0) 1}}
````

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
