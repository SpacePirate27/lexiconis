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
                    (if (and ~@(for [[k v] (::spec/if body)]
                                 `(= ~v (~k ~input-sym))))
                      (dispatcher/dispatch ~(::spec/then body))
                      (throw (Exception. "Rule failed for input")))))
      `(throw (Exception. (s/explain-str ::spec/rule-spec ~body))))))
