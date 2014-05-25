(defproject roomlist "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [http-kit "2.1.18"]
                 [ring "1.2.2"]
                 [compojure "1.1.8"]
                 [com.taoensso/sente "0.14.0"] 
                 [reagent "0.4.2"]
                 [org.clojure/tools.logging "0.2.6"]
                 [ch.qos.logback/logback-classic "1.1.2"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [com.datomic/datomic-free "0.9.4755" :exclusions [org.slf4j/slf4j-nop
                                                                   org.slf4j/slf4j-log4j12]]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.10"]]
  :source-paths ["src/clj"]
  :hooks [leiningen.cljsbuild]
  :cljsbuild { 
    :builds {
      :main {
        :source-paths ["src/cljs"]
        :compiler {:output-to "resources/public/js/main.min.js"
                   :optimizations :whitespace
                   :pretty-print true}
        :jar true}}}
  :main roomlist.server
)

