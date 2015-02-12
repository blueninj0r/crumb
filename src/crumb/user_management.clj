(ns crumb.user-management
  (:require  [net.cgrand.enlive-html :as html]
             [crumb.db-service :as db]))

(html/deftemplate create-account-template "create-account.html"
  [messages]
  [:div.messages] (html/html-content (reduce str (map #(str "<p>" %1 "</p>") messages))))

(defn attempt-create-user-account
  [username]
  (let [usr-exists (db/user-exists? username)
        result {:user-created false :messages ["User created!"]}]
    (if usr-exists
      (assoc result :messages ["This username is already taken."])
      (do
        (db/create-user-account username)
        (assoc result :user-created true)))))

(defn create-user-account
  [username]
  (let [usr-response (attempt-create-user-account username)]
    (if (:user-created usr-response)
      "GREAT"
      (create-account-template (:messages usr-response)))))
