(ns lexiconis.dispatcher)

(defmulti perform-action
          "Performs actions after dispatching based on key of input map"
          (fn [m] (first (keys m))))

(defmethod perform-action :lights
  [m]
  (println (str "Lights are now " (name (:lights m))))
  {:action :lights
   :status true})

(defmethod perform-action :tv
  [m]
  (println (str "TV is now " (name (:tv m))))
  {:action :tv
   :status true})

(defmethod perform-action :ac
  [m]
  (println (str "Air Conditioner is now " (name (:ac m))))
  {:action :ac
   :status true})

(defmethod perform-action :coffee-machine
  [m]
  (println (str "Coffee Machine is now " (name (:coffee-machine m))))
  {:action :coffee-machine
   :status true})

(defmethod perform-action :blinds
  [m]
  (println (str "Blinds are now " (name (:blinds m))))
  {:action :blinds
   :status true})

(defmethod perform-action :gas-valve
  [m]
  (println (str "Gas Valve is now  " (name (:gas-valve m))))
  {:action :gas-valve
   :status true})

(defmethod perform-action :alarm
  [m]
  (println (str "Alarm is now  " (name (:alarm m))))
  {:action :alarm
   :status true})

(defmethod perform-action :notification
  [m]
  (println (str "Notification has been " (name (:notification m))))
  {:action :notification
   :status true})

(defn dispatch
  "Calls perform action for all actions"
  [actions]
  (mapv perform-action actions))
