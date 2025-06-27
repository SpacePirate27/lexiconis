(ns lexiconis.core-test
  (:require [clojure.test :refer :all]
            [lexiconis.specs :as spec]
            [lexiconis.core :as core]))

(deftest sleep-time-test
  (testing "Given a valid sleep-time fact, actions are performed"
    (is (= [{:action :ac :status true}
            {:action :tv :status true}
            {:action :lights :status true}]
           (core/sleep-time {::spec/room-type  :bedroom
                             ::spec/time-input 1751034400000}))))
  (testing "Given a invalid sleep-time fact, rule should fail"
    (is (= {:rule :failed :status false} (core/sleep-time {::spec/room-type  :kitchen
                                                           ::spec/time-input 1751034400000}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/sleep-time {::spec/a :b})))))

(deftest morning-coffee-test
  (testing "Given a valid morning-coffee fact, actions are performed"
    (is (= [{:action :coffee-machine :status true}]
           (core/morning-coffee {::spec/room-type  :kitchen
                                 ::spec/time-input 1750998610000}))))
  (testing "Given a invalid morning-coffee fact, rule should fail"
    (is (= {:rule :failed :status false} (core/morning-coffee {::spec/room-type  :hall
                                                               ::spec/time-input 1751034400000}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/morning-coffee {::spec/a :b})))))

(deftest blinding-lights-test
  (testing "Given a valid blinding-lights fact, actions are performed"
    (is (= [{:action :blinds :status true}]
           (core/blinding-lights {::spec/room-type       :hall
                                  ::spec/time-input      1751002900000
                                  ::spec/lux-level-input 15000}))))
  (testing "Given a invalid blinding-lights fact, rule should fail"
    (is (= {:rule :failed :status false} (core/blinding-lights {::spec/room-type  :kitchen
                                                                ::spec/time-input 1751002900000}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/blinding-lights {::spec/a :b})))))

(deftest no-tv-test
  (testing "Given a valid no-tv fact, actions are performed"
    (is (= [{:action :tv :status true}]
           (core/no-tv {::spec/room-type    :hall
                        ::spec/motion-input false}))))
  (testing "Given a invalid no-tv fact, rule should fail"
    (is (= {:rule :failed :status false} (core/no-tv {::spec/room-type    :kitchen
                                                      ::spec/motion-input true}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/no-tv {::spec/a :b})))))


(deftest holy-smokes
  (testing "Given a valid holy-smokes fact, actions are performed"
    (is (= [{:action :gas-valve :status true}
            {:action :alarm :status true}
            {:action :notification :status true}]
           (core/holy-smokes {::spec/room-type   :kitchen
                              ::spec/smoke-input true}))))
  (testing "Given a invalid holy-smokes fact, rule should fail"
    (is (= {:rule :failed :status false} (core/holy-smokes {::spec/room-type   :bedroom
                                                            ::spec/smoke-input false}))))
  (testing "Given an invalid input fact, input validation should fail"
    (is (= {:input-validation :failed :status false} (core/holy-smokes {::spec/a :b})))))
