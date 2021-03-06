(ns algorithm.min-coins)

(defn- index-combinations
  [n cnt]
  (lazy-seq
    (let [c (vec (cons nil (for [j (range 1 (inc n))] (+ j cnt (- (inc n)))))),
          iter-comb
          (fn iter-comb [c j]
            (if (> j n) nil
              (let [c (assoc c j (dec (c j)))]
                (if (< (c j) j) [c (inc j)]
                  (loop [c c, j j]
                    (if (= j 1) [c j]
                      (recur (assoc c (dec j) (dec (c j))) (dec j)))))))),
          step
          (fn step [c j]
            (cons (rseq (subvec c 1 (inc n)))
              (lazy-seq (let [next-step (iter-comb c j)]
                          (when next-step (step (next-step 0) (next-step 1)))))))]
      (step c 1))))

(defn combinations
  "All the unique ways of taking n different elements from items"
  [items n]
  (let [v-items (vec (reverse items))]
    (if (zero? n) (list ())
      (let [cnt (count items)]
        (cond (> n cnt) nil
          (= n cnt) (list (seq items))
          :else
          (map #(map v-items %) (index-combinations n cnt)))))))

;;(println (combinations [1 2 3 4 5] 3))

(defn min-coins
  [a s]
  (let [c (count a)]
    (loop [cc 3]
      (if (> cc c)
        {-1, '()}
        (let [fca (for [ca (combinations a cc) :when (= (apply + ca) s)] ca)]
          (if (> (count fca) 0)
            {cc (distinct fca)}
            (recur (inc cc))))))))
        
;;(min-coins [1 2 5 5] 8)
