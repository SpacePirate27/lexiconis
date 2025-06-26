(ns lexiconis.dispatcher)

(defmulti perform-action
          (fn [m]
     (first (keys m))))

(defmethod perform-action :lights
  [m]
  (println (str "Lights are now " (name (:lights m)))))

(defmethod perform-action :tv
  [m]
  (println (str "TV is now " (name (:tv m)))))

(defmethod perform-action :ac
  [m]
  (println (str "Air Conditioner is now " (name (:ac m)))))

(defmethod perform-action :coffee-machine
  [m]
  (println (str "Coffee Machine is now " (name (:coffee-machine m)))))

(defmethod perform-action :blinds
  [m]
  (println (str "Blinds are now " (name (:blinds m)))))

(defmethod perform-action :gas-valve
  [m]
  (println (str "Gas Valve is now  " (name (:gas-valve m)))))

(defmethod perform-action :alarm
  [m]
  (println (str "Alarm is now  " (name (:alarm m)))))

(defmethod perform-action :notification
  [m]
  (println (str "Notification has been " (name (:notification m)))))

(defn dispatch
  [actions]
  (map perform-action actions))
