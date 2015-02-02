(ns crumb.create-layout
  (:require [hiccup.form :as f]
            [hiccup.page :as p]
            [hiccup.core :as c]
            [namban.boeki :as nam]))

(defn form
  []
  (p/html5
   (f/form-to [:post "/"]
               (f/text-field :word))))

(defn translation
  [word]
  (let [translated (nam/romaji->hiragana word)]
    (p/html5
     (c/html
      [:p translated]))))
