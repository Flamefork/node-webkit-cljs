(defproject example "0.1.0-SNAPSHOT"
  :description "node-webkit-cljs example project"
  :min-lein-version "2.0.0"

  :dependencies [[jayq "2.0.0"] ; just for example
                 [node-webkit-cljs "0.1.3"]]

  :plugins [[lein-cljsbuild "0.3.0"]]
  
  :cljsbuild {:builds [{:source-paths ["src"]
                        :incremental false ; https://github.com/emezeske/lein-cljsbuild/issues/181
                        :compiler {
                                    :output-to "js/application.js"
                                    :optimizations :whitespace
                                    :warnings true
                                    :pretty-print true}}]})
