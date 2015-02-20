(ns bootstrap
  (:require [clojure.java.jdbc :as jdbc]))

(def pgdb
  { :subprotocol "postgresql"
   :subname "//localhost:5432/crumb" })

(def drop-all-tables-sql
  "drop table users; drop table wordbank;")

(def create-users-table-sql
  "create table users (
id bigserial primary key,
username varchar(255)
);")

(def create-wordbank-table-sql
  "create table wordbank (
user_id bigint references users(id),
english_word varchar(255),
romaji_word varchar(255),
japanese_word varchar(255)
);")

(jdbc/execute! pgdb [create-wordbank-table-sql])
