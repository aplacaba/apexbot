(ns apex-bot.parsers
  (:require [clojure.data.json :as json]
            [clojure.string :as s]))

(defn fmt-map-rotation [data]
  (let [current (:current data)
        nxt     (:next data)]
    (str
     "```"
     "Current Map: " (:map current) "\n"
     "Remaining Time: " (:remainingTimer current) "\n"
     "Next Map: " (:map nxt) "\n"
     "Duration: " (:DurationInMinutes nxt) " minutes"
     "```")))


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
    (str
     "**Dailies**\n"
     (crafter-items dailies)
     "\n\n"
     "**Weeklies**\n"
     (crafter-items weeklies))))
