(ns apex-bot.api
  (:require [clj-http.client :as client]
            [environ.core    :refer [env]]
            [clojure.data.json :as json]))

(def url (env :apex-api))
(def api-key (env :apex-api-key))

(defn proc-request
  [endpoint]
  (let [resp (client/get endpoint)
        body (:body resp)]
    (json/read-str body :key-fn keyword)))

(defn map-rotation []
  (let [endpoint (str url "/maprotation?auth=" api-key)]
    (proc-request endpoint)))

(defn crafting-rotation []
  (let [endpoint (str url "/crafting?auth=" api-key)]
    (proc-request endpoint)))
