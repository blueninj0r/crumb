(ns bootstrap
  (:require [clojure.java.jdbc :as jdbc]))

(def pgdb
  { :subprotocol "postgresql"
   :subname "//localhost:5432/crumb" })

(def create-users-table-sql
  "create table users (
id bigserial primary key,
username varchar(255)
);")

(jdbc/execute! pgdb [create-users-table-sql])
