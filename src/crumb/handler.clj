(ns crumb.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [crumb.user-management :as users]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/create-account" []  (users/create-account-template []))
  (POST "/create-account" {{username :username} :params} (users/create-user-account username))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
