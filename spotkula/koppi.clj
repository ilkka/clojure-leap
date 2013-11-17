(ns clojure-leap.spotkula.koppi
  (:require [clojure-leap.core :as leap]
            [clojure-leap.gestures :as gestures]))

;; will store frame id limit for reacting, this will help limit
;; false positives
(def threshold
  "Frame ID threshold for frame to be considered part of the previous gesture"
  (atom 0)) 

(defn fast?
  "Is speed fast in absolute terms?"
  [speed]
  (> 1000 (Math/abs speed)))

(defn gesture-threshold?
  "Is frame past the detection threshold of the previous gesture?"
  [frame] 
  (> (.id frame) @threshold))

(defn skip-track
  "Cause player to skip to next track"
  nil)

(defn process-frame
  "Process frames. Detects things moving fast in either the positive or negative Z direction."
  [controller frame screens]
  (when-let [hand (and (leap/hands? frame) (leap/lowest-hand frame))]
    (let [speed (.getZ (.palmVelocity hand))]
      (when (and (fast? speed) (> (.id frame) @threshold))
        (skip-track)
        (reset! threshold (+ gestures/*threshold* (.id frame)))))))

(defn -main [& args]
  (let [listener (leap/listener :frame #(process-frame (:controller %) (:frame %) (:screens %)))
        [controller _] (leap/controller listener)]
    (println "Press Enter to quit")
    (read-line)
    (leap/remove-listener! controller listener)))

