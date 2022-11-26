(ns apex-bot.core
  (:require
   [clojure.core.async    :as a]
   [discljord.connections :as c]
   [discljord.messaging   :as m]
   [environ.core          :refer [env]]
   [apex-bot.api          :as api]
   [apex-bot.parsers      :as parser]
   [clojure.string        :as s])
  (:gen-class))

(def token (env :discord-key))
(def intents #{:guilds :guild-messages})
(def channel-id (env :target-channel))

(defn show-map-rotation []
  (parser/fmt-map-rotation (api/map-rotation)))

(defn show-crafting []
  (parser/fmt-crafting-rotation (api/crafting-rotation)))

(def show-commands
  (str
   "```"
   "/maprotation - View current map rotations\n"
   "/crafting - View daily and weekly crafting items\n"
   "/commands - Show available commands"
   "```"))

(defn -main [& args]
  (let [event-ch      (a/chan 100)
        connection-ch (c/connect-bot! token event-ch :intents intents)
        message-ch    (m/start-connection! token)]
    (try
      (loop []
        (let [[event-type event-data] (a/<!! event-ch)]
          (when (and (= :message-create event-type)
                     (= (:channel-id event-data) channel-id)
                     (not (:bot (:author event-data))))
            (let [message-content (:content event-data)
                  content (s/trim (s/lower-case message-content))]
              (case content
                "/maprotation" (m/create-message! message-ch channel-id :content (show-map-rotation))
                "/crafting" (m/create-message! message-ch channel-id :content (show-crafting))
                "/commands" (m/create-message! message-ch channel-id :content show-commands)
                "default")))
          (when (= :channel-pins-update event-type)
            (c/disconnect-bot! connection-ch))
          (when-not (= :disconnect event-type)
            (recur))))
      (finally
        (m/stop-connection! message-ch)
        (a/close!           event-ch)))))
