(ns lexiconis.specs-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [lexiconis.specs :as spec]))

(defspec rules-spec-test
  100
  (prop/for-all [rule (s/gen ::spec/rule-spec)]
    (s/valid? ::spec/rule-spec rule)))

(defspec fact-spec-test
  100
  (prop/for-all [rule (s/gen ::spec/fact-spec)]
    (s/valid? ::spec/fact-spec rule)))
