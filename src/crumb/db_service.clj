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

(defn create-wordbank-entry
  [user-id english-word romaji-word japanese-word]
  (jdbc/insert! pgdb :wordbank {
                                :user_id user-id
                                :english_word english-word
                                :japanese_word japanese-word
                                :romaji_word romaji-word}))
