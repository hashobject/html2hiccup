(ns html2hiccup.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"}))

(om/root
  (fn [app owner]
    (html [:div.row
           [:div.column.large-6
            [:textarea {:placeholder "Your html"}]]
           [:div.column.large-6
            [:textarea {:placeholder "Your hiccup"}]]
           [:div.column.large-9.large-centered
            [:button.button "Convert"]]]
          ))
  app-state
  {:target (. js/document (getElementById "app"))})
