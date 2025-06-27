(ns lexiconis.rule
  (:require [clojure.spec.alpha :as s]
            [lexiconis.specs :as spec]
            [lexiconis.dispatcher :as dispatcher]))

(def op-map
  {:eq =
   :gt >
   :lt <})

(def key-map
  {::spec/room      ::spec/room-type
   ::spec/time      ::spec/time-input
   ::spec/motion    ::spec/motion-input
   ::spec/lux-level ::spec/lux-level-input
   ::spec/smoke     ::spec/smoke-input})

(defmacro defrule
  "Returns a function that is validated according to rule-spec. The function evaluates
  the rules and when it passes performs all the side effects that the rule defines."
  [name body]
  (let [input-sym (gensym "input")]
    (if (s/valid? ::spec/rule-spec body)
      `(def ~name (fn [~input-sym]
                    (if (s/valid? ::spec/fact-spec ~input-sym)
                      (if (and ~@(for [[k v] (::spec/if body)]
                                   `(((first ~v) op-map) ((key-map ~k) ~input-sym) (second ~v))))
                        (do
                          (println "Rule matched!")
                          (dispatcher/dispatch ~(::spec/then body)))
                        (do
                          (println "Rule failed for input")
                          {:rule :failed :status false}))
                      (do
                        (println "Input validation failed. Invalid input" (s/explain-str ::spec/fact-spec ~input-sym))
                        {:input-validation :failed :status false}))))
      `(do
         (println "Rule validation failed. Invalid rule. " (s/explain-str ::spec/rule-spec ~body))
         {:rule-validation :failed :status false}))))
