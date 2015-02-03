(ns crumb.test-layout
  (:require [hiccup.core :as c]
            [hiccup.page :as p]
            [hiccup.form :as f]))

(defn test-submission-page
  [word]
  (p/html5
   (c/html
    [:p (str "Please translate: " word)]
    (f/form-to [:post "/test"]
               (f/text-field "answer")))))

(defn test-result-page
  [attempt actual]
  (p/html5
   (c/html
    [:p (str "Your answer: " attempt)]
    [:p (str "The correct answer: " actual)]
    [:p (if (= attempt actual) "Correct!" "Wrong!")])))
