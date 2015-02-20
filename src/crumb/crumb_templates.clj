(ns crumb.crumb-templates
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate add-word-template "add-word.html"
  [x]
  )

(html/deftemplate wordbank-template "wordbank.html" [])
