(ns apex-bot.api
  (:require [clj-http.client :as client]
            [environ.core    :refer [env]]))

(def url (env :apex-api))
(def api-key (env :apex-api-key))

(defn map-rotation
  []
  (client/get
   (str url "/maprotation?auth=" api-key)))

(defn crafting-rotation
  []
  (client/get
   (str url "/crafting?auth=" api-key)))
