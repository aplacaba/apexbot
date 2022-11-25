(ns apex-bot.core
  (:require
   [clojure.core.async    :as a]
   [discljord.connections :as c]
   [discljord.messaging   :as m]
   [environ.core          :refer [env]]))

(def token (env :discord-key))
(def intents #{:guilds :guild-messages})
(def channel-id (env :target-channel))

;; (let [event-ch (a/chan 100)
;;       connection-ch (c/connect-bot! token event-ch :intents intents)
;;       message-ch (m/start-connection! token)]
;;   (try
;;     (loop []
;;       (let [[event-type event-data] (a/<!! event-ch)]
;;         (when (and (= :message-create event-type)
;;                    (= (:channel-id event-data) channel-id)
;;                    (not (:bot (:author event-data))))
;;           (m/create-message! message-ch channel-id :content "Hello World!"))
;;         (when (= :channel-pins-update event-type)
;;           (c/disconnect-bot! connection-ch))
;;         (when-not (= :disconnect event-type)
;;           (recur))))
;;     (finally
;;       (m/stop-connection! message-ch)
;;       (a/close!           event-ch))))
