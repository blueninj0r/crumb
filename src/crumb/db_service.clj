(ns crumb.db-service
  (:require [clojure.java.jdbc :as jdbc]))

(def pgdb
  { :subprotocol "postgresql"
   :subname "//localhost:5432/crumb" })

(defn user-exists?
  [username]
  (let [result (jdbc/query pgdb [(format "SELECT COUNT(*) FROM users WHERE username LIKE '%s'" username)])]
    (-> result first :count (not= 0))))

(defn create-user-account
  [username]
  (jdbc/insert! pgdb :users {:username username}))
