(ns crumb.wordbank-service
  (:require [crumb.db-service :as db]
            [namban.boeki :as n]))


(def available-translators
  {:hiragana n/romaji->hiragana :katakana n/romaji->katakana})

(defn translate-word
  [romaji-word syllabary]
  (let [translators (map #(%1 available-translators) syllabary)]
    (map #(%1 romaji-word) translators)))

(defn create-wordbank-entry
  [user-id english-word romaji-word & syllabary]
  (let [translated-terms (translate-word romaji-word (vec syllabary))]
    (map #(db/create-wordbank-entry user-id english-word romaji-word %1) translated-terms)))
