(ns lexiconis.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::room #{:bedroom :hall :kitchen})

(s/def ::state #{:on :off :dim :send})

(s/def ::time int?)

(s/def ::lux-level int?)

(s/def ::motion boolean?)

(s/def ::smoke boolean?)

(s/def ::object #{:lights :tv :ac :coffee-machine :blinds :gas-valve :alarm :notification})

(s/def ::rule-id string?)

(s/def ::if (s/keys :req [::room]
                    :opt [::time ::lux-level ::motion ::smoke]))

(s/def ::action (s/map-of ::object ::state))

(s/def ::then (s/coll-of ::action))

(s/def ::rule-spec (s/keys :req [::rule-id ::if ::then]))

(s/def ::fact-spec (s/keys :req [::room]
                           :opt [::time ::lux-level ::motion ::smoke]))
