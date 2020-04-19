(defproject pets-api "0.1.0-SNAPSHOT"
  :description "Pets API developed by Guilherme Melo"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [metosin/compojure-api "1.1.13"]

                 [prismatic/schema "1.1.12"]

                 ;crypt library
                 [crypto-password "0.2.1"]

                 ; metrics library
                 [metrics-clojure "2.8.0"]
                 [metrics-clojure-ring "2.8.0"]

                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [nano-id "0.9.3"]
                 [korma "0.4.3"]
                 [mysql/mysql-connector-java "8.0.19"]]

  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler pets-api.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]
                                  [midje "1.9.3"]]
                   :plugins [[lein-midje "3.2.1"]
                             [lein-cloverage "1.0.13"]]}
             :uberjar {:aot :all}}
  :main pets-api.core)
