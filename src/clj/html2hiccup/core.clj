(ns html2hiccup.core
  (:require [ring.util.response :refer [file-response]]
            [compojure.core :refer [defroutes GET PUT]]
            [compojure.route :as route]
            [compojure.handler :as handler]))

(defn index []
  (file-response "public/index.html" {:root "resources"}))

(defroutes routes
  (GET "/" [] (index))
  (GET "/html-hiccup" [] "Hey man")
  (route/files "/" {:root "resources/public"}))

(def app
  (-> routes
      handler/site))
