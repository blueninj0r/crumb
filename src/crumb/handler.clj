(ns crumb.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [crumb.create-layout :as create]))

(defroutes app-routes
  (GET "/" [] (create/form))
  (POST "/" {{word :word} :params} (create/translation word))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
