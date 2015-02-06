(ns crumb.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [net.cgrand.enlive-html :as html]))

(html/deftemplate create-account-template "create-account.html"
  [messages]
  [:div.messages] (html/html-content (reduce str (map #(str "<p>" %1 "</p>") messages))))

(defn attempt-create-user-account
  [username]
  {:user-created? false :messages ["Username already exists", "You are a bad person"]})

(defn create-user-account
  [username]
  (let [usr-response (attempt-create-user-account username)]
    (if (:user-created? usr-response)
      "GREAT"
      (create-account-template (:messages usr-response)))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/create-account" []  (create-account-template []))
  (POST "/create-account" {{username :username} :params} (create-user-account username))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
