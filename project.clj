(defproject html2hiccup "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://html2hiccup.hashobject.com"
  :signing {:gpg-key "Hashobject Ltd <team@hashobject.com>"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [ring/ring-core "1.2.1"]
                 [ring-server "0.3.1"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [compojure "1.1.6"]
                 [environ "0.4.0"]
                 [hiccup "1.0.5"]
                 [hickory "0.5.2"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [om "0.4.1"]
                 [com.facebook/react "0.8.0.1"]
                 [sablono "0.2.6"]
                 [org.clojure/tools.reader "0.8.3"]]


  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["resources"]

  :plugins [[lein-ring "0.8.10"]
            [lein-cljsbuild "1.0.2"]]
  :ring {:handler html2hiccup.core/app}


  :cljsbuild {
    :builds [{:id "app"
              :source-paths ["src/clj" "src/cljs"]
              :compiler {
                :output-to "resources/public/app.js"
                :output-dir "resources/public/out"
                :optimizations :none
                :source-map true}}]})
