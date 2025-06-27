(ns lexiconis.core
  (:require [lexiconis.rule :refer [defrule]]
            [lexiconis.specs :as spec]))

(defrule sleep-time {::spec/rule-id "Sleep"
                     ::spec/if      {::spec/room [:eq :bedroom]
                                     ::spec/time [:gt 1751033400000]}
                     ::spec/then    [{:ac :on}
                                     {:tv :off}
                                     {:lights :dim}]})

(defrule morning-coffee {::spec/rule-id "Morning Coffee"
                         ::spec/if      {::spec/room [:eq :kitchen]
                                         ::spec/time [:gt 1750998600000]}
                         ::spec/then    [{:coffee-machine :on}]})

(defrule blinding-lights {::spec/rule-id "Blinding Lights"
                          ::spec/if      {::spec/room      [:eq :hall]
                                          ::spec/time      [:gt 1751002800000]
                                          ::spec/lux-level [:gt 10000]}
                          ::spec/then    [{:blinds :off}]})

(defrule no-tv {::spec/rule-id "No TV"
                ::spec/if      {::spec/room   [:eq :hall]
                                ::spec/motion [:eq false]}
                ::spec/then    [{:tv :off}]})

(defrule holy-smokes {::spec/rule-id "Holy Smokes"
                      ::spec/if      {::spec/room  [:eq :kitchen]
                                      ::spec/smoke [:eq true]}
                      ::spec/then    [{:gas-valve :off}
                                      {:alarm :on}
                                      {:notification :send}]})
