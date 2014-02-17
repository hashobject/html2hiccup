
(ns html2hiccup.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require
            ;[om.core :as om :include-macros true]
            ;[om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! <! >! chan timeout]]
    ;        [cljs-http.client :as http]
            [hickory.core :as hick]
   ;         [sablono.core :as html :refer-macros [html]]
   ))

(enable-console-print!)


;(def html-code-el (. js/CodeMirror fromTextArea (. js/document (getElementById "html-code")) {:mode "htmlembedded"}))

;(def hiccup-code-el (. js/CodeMirror fromTextArea (. js/document (getElementById "hiccup-code")) {:mode "clojure"}))

(comment
(def app-state (atom {:html "<p>Put your html here</p>"
                      :hiccup "[:p 'And get your Hiccup here']"}))


(defn- value-from-node
  [component field]
  (let [n (om/get-node component field)
        v (-> n .-value clojure.string/trim)]
    (when-not (empty? v)
      [v n])))


(defn convert-html!
  [html url app]
  (go (let [res (<! (http/get url {:html html}))]
        (if (= 200 (:status res))
          (om/transact! app [:hiccup]
                    (fn [] (:body res)))
          (print "not cool")
          ))))


(defn handle-submit
  [e app owner]
  (let [[html html-node] (value-from-node owner "html-code")]
    ;(convert-html! html "/html-hiccup" app)
    ;(print (hick/as-hiccup (hick/parse html)))
    (. hiccup-code-el setValue (hick/as-hiccup (hick/parse html)))
    ;(om/transact! app [:hiccup] (fn [] (hick/as-hiccup (hick/parse html))))
    false)
   false)

(defn change [e prop app]
  (om/transact! app prop #(.. e -target -value)))

(om/root
  (fn [app owner]
    (reify
      om/IRender
      (render [_]
        (html [:div.row ;{:onSubmit #(handle-submit % app owner)}
               [:div.column.large-6
                [:textarea {:id "html-code"
                            :ref "html-code"
                            :placeholder "Your html"
                            :value (:html app)
                            :onChange (fn [e]
                                        (change e :html app)
                                        (. htmlCodeEl setValue "xx"))
                            }]]
               [:div.column.large-6
                [:textarea {:id "hiccup-code"
                            :placeholder "Your hiccup"
                            :value (:hiccup app)
                            :onChange #(change % :hiccup app)}]]
               [:div.column.large-9.large-centered
                [:button.button
                 {:onClick (fn [_]
                             (let [[html html-node] (value-from-node owner "html-code")]
                             ;(. hiccupCodeEl setValue (hick/as-hiccup (hick/parse html)))
                               ))
                              }
                 "Convert"]]]
              ))))
  app-state
  {:target (. js/document (getElementById "app"))})
)


(defn init-cm [el readonly]
  (. js/CodeMirror fromTextArea
                     (. js/document (getElementById el))
                     (clj->js {"readOnly" readonly})))
(defn app []
  (let [html-code (init-cm "html-code" false)
        hiccup-code (init-cm "hiccup-code" true)]
    (. html-code on "change" (fn [editor]
                               (let [html (.getValue editor)
                                     hiccup (hick/as-hiccup (hick/parse html))
                                     hiccup-str (prn-str hiccup)
                                     fixed-str (clojure.string/replace hiccup-str #"\"\\n\"" "\r\n")
                                     ]
                                   (print hiccup-str fixed-str)
                                   (.setValue hiccup-code fixed-str)
                                 )

                                                   ))))

(app)

