(ns apex-bot.parsers
  (:require [clojure.data.json :as json]))

(defn fmt-map-rotation [data]
  (let [current (:current data)
        nxt     (:next data)]
    (format "

Current Map: %s
Remaining Time: %s
Next Map: %s
Duration: %s minutes
"
            (:map current)
            (:remainingTimer current)
            (:map nxt)
            (:DurationInMinutes nxt))))

(defn fmt-crafting-rotation [data]
  )
