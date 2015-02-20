(ns crumb.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [crumb.user-management :as users]
            [crumb.crumb-templates :as t]
            [crumb.wordbank-service :as wb]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/create-account" []  (users/create-account-template []))
  (POST "/create-account" {{username :username} :params} (users/create-user-account username))
  (GET "/add-word" [] (t/add-word-template 1))
  (POST "/add-word" {{english-word :english-word romaji-word :romaji-word} :params} (wb/create-wordbank-entry 1 english-word romaji-word :hiragana :katakana))
  (GET "/wordbank" [] (t/wordbank-template))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
