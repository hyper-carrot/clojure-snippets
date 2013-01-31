(ns intro.break_down)

(defn ask
  "Ask for an input."
  ([] (ask "Please Input"))
  ([^String prompt]
    (printf "%s: " prompt)
    (flush)
    (.readLine *in*)))

(defn break-down[]
  "Break down into subprocesses: read -> eval."
  (let [prompt ">>>" exit-cmd "8"]
    (loop [t-form (ask prompt)]
      (if (= t-form exit-cmd)
        "||OK!||"
        (let [o-form (read-string t-form)
              result (eval o-form)]
          (apply println (take 20 (repeat "-")))
          (println (str "received t-form: " t-form))
          (println (str "type of o-form: " (type o-form)))
          (println (str "value of o-form: " result))
          (apply println (take 20 (repeat "-")))
          (recur (ask prompt)))))))

(break-down)
