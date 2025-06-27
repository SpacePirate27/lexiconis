(ns lexiconis.specs
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(s/def ::op #{:eq :lt :gt})

(s/def ::room-type #{:bedroom :hall :kitchen})

(s/def ::room (s/spec (s/cat :op ::op :room-type ::room-type)))

(s/def ::state #{:on :off :dim :send})

(s/def ::time (s/spec (s/cat :op ::op :value int?)))

(s/def ::lux-level (s/spec (s/cat :op ::op :value int?)))

(s/def ::time-input int?)

(s/def ::lux-level-input int?)

(s/def ::motion boolean?)

(s/def ::smoke boolean?)

(s/def ::object #{:lights :tv :ac :coffee-machine :blinds :gas-valve :alarm :notification})

(s/def ::rule-id string?)

(s/def ::if (s/keys :req [::room]
                    :opt [::time ::lux-level ::motion ::smoke]))

(s/def ::action (s/map-of ::object ::state :max-count 1 :min-count 1))

(s/def ::then (s/coll-of ::action))

(s/def ::rule-spec (s/keys :req [::rule-id ::if ::then]))

(s/def ::fact-spec (s/keys :req [::room-type]
                           :opt [::time-input ::lux-level-input ::motion ::smoke]))
