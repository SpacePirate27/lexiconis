(ns lexiconis.rule
  (:require [clojure.spec.alpha :as s]
            [lexiconis.specs :as spec]
            [lexiconis.dispatcher :as dispatcher]))

(defmacro defrule
  "Returns a function that is validated according to rule-spec. The function evaluates
  the rules and when it passes performs all the side effects that the rule defines."
  [name body]
  (let [input-sym (gensym "input")]
    (if (s/valid? ::spec/rule-spec body)
      `(def ~name (fn [~input-sym]
                    (if (s/valid? ::spec/fact-spec ~input-sym)
                      (if (and ~@(for [[k v] (::spec/if body)]
                                   `(= ~v (~k ~input-sym))))
                        (do
                          (println "Rule matched!")
                          (dispatcher/dispatch ~(::spec/then body)))
                        (println "Rule failed for input"))
                      (println "Input validation failed. Invalid input" (s/explain-str ::spec/fact-spec ~input-sym)))))
      `(println "Rule validation failed. Invalid rule. " (s/explain-str ::spec/rule-spec ~body)))))
