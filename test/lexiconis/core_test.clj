(ns lexiconis.core-test
  (:require [clojure.test :refer :all]
            [lexiconis.specs :as spec]
            [lexiconis.core :as core]))

(deftest sleep-time-test
  (testing "Given a valid sleep-time fact, actions are performed"
    (is (= [{:action :ac :status true}
            {:action :tv :status true}
            {:action :lights :status true}]
           (core/sleep-time {::spec/room-type :bedroom
                             ::spec/time-input 1751034400000}))))
  (testing "Given a invalid sleep-time fact, rule should fail"
    (is (= {:rule :failed :status false} (core/sleep-time {::spec/room-type :kitchen
                                                           ::spec/time-input 1751034400000}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/sleep-time {::spec/a :b})))))

