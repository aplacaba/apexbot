(ns apex-bot.parsers
  (:require [clojure.data.json :as json]
            [clojure.string :as s]))

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

(defn crafter-items [col]
  (let [item-fmt (map (fn [item]
                       (str
                        "name: " (:name (:itemType item))
                        "\n"
                        "image: " (:asset (:itemType item)))) col)]
    (s/join "\n" (into [] item-fmt))))

(defn fmt-crafting-rotation [data]
  (let [dailies  (first (map #(:bundleContent %)
                     (filter #(= (:bundleType %) "daily") data)))
        weeklies (first (map #(:bundleContent %)
                             (filter #(= (:bundleType %) "weekly") data)))]

    (format "
*Dailies*
%s
*Weeklies*
%s
"
            (crafter-items dailies)
            (crafter-items weeklies))))
