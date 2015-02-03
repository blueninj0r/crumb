(ns crumb.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [crumb.create-layout :as create]
            [crumb.test-layout :as test]
            [namban.boeki :as namban]))

(defn get-test-word
  []
  (namban/romaji->hiragana "watashi"))

(defroutes app-routes
  (GET "/" [] (create/form))
  (POST "/" {{word :word} :params} (create/translation word))
  (GET "/test" [] (test/test-submission-page (get-test-word)))
  (POST "/test" {{answer :answer} :params} (test/test-result-page answer "watashi"))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
