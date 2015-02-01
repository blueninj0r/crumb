(ns crumb.create-layout
  (:require [hiccup.form :as f]
            [hiccup.core :as c]))

(defn form
  []
  (c/html
   ( f/form-to [:post "/"]
               (f/text-field :word))))
