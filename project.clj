(defproject html2hiccup "0.1.0-SNAPSHOT"
  :description "Html/Hiccup online converter"
  :url "http://html2hiccup.hashobject.com"
  :signing {:gpg-key "Hashobject Ltd <team@hashobject.com>"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [hickory "0.5.2"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [org.clojure/tools.reader "0.8.3"]]


  :source-paths ["src/cljs"]
  :resource-paths ["resources"]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/app.js"
                :output-dir "resources/public/out"
                :optimizations :none
                :source-map true}}
            {:id "prod"
              :source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/app.js"
                :optimizations :advanced
                :elide-asserts true
                :pretty-print false
                :output-wrapper false
                :source-map false}}]})
