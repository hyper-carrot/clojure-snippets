(ns intro.fp_features)


;; Immutable data
(def a [1 2])
(def b (cons 0 a))
;; a -> '(1 2)
(println "The 'a' is " a)
;; b -> 0 append a
(println "The 'b' is " b)


;; the function as a 'first-class' 1
(defn my-func1
  "A demo of first-class"
  [d f]
  (f d))
(my-func1 "It's first-class!" println)


;; the function as a 'first-class' 2
(defn func-a [s] (str "Func A: " s))
(defn func-b [s] (str "Func B: " s))
(defn my-func2
  "Another demo of first-class"
  [n]
  (cond
    (> n 0) func-a
    :else func-b))
(println ((my-func2 0) "my-first-class"))


;; lazy and infinite sequences

;; (iterate inc 1) ;; Don't do that!!
(println (take 10 (iterate inc 1)))

;; a lazy fibonacci sequence
(defn lazy-seq-fibo
  ([]
    (concat [0 1] (lazy-seq-fibo 0 1)))
  ([a b]
    (let [n (+ a b)]
      (lazy-seq
        (cons n (lazy-seq-fibo b n))))))

(println (take 10 (lazy-seq-fibo)))

;; closure
(defn double-op [f] (fn [& args] (* 2 (apply f args))))
(def double-add (double-op +))
(def double-sub (double-op -))
(def double-multi (double-op *))
(def double-div (double-op /))
(do (vector 
      (double-add 1 2 3) (double-sub 9 4) (double-multi 4 5) (double-div 12 3)))
    