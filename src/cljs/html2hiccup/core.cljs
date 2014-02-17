(ns html2hiccup.core
  (:require [hickory.core :as hick]))

(enable-console-print!)


(defn init-cm [el readonly]
  (. js/CodeMirror fromTextArea
                     (. js/document (getElementById el))
                     (clj->js {"readOnly" readonly})))

(defn on-html-change [editor]
  (let [html (.getValue editor)
        hiccup (hick/as-hiccup (hick/parse html))
        hiccup-str (prn-str hiccup)
        fixed-str (clojure.string/replace hiccup-str #"\"\\n\"" "\r\n")]
       (.setValue hiccup-code fixed-str)))

(defn app []
  (let [html-code (init-cm "html-code" false)
        hiccup-code (init-cm "hiccup-code" true)]
    (. html-code on "change" on-html-change)))

(app)

