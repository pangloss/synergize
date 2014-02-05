(defproject xnlogic/synergize "0.1.0-SNAPSHOT"
  :clojurescript? true
  :description "Synergize Javascript objects with ClojureScript's powerful protocols in a safe way"
  :url "https://github.com/pangloss/synergize"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies  [[org.clojure/clojure "1.5.1"]
                  [org.clojure/clojurescript "0.0-2156"]]
  :plugins [[lein-cljsbuild "0.3.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild
  {:builds
   {:dev
    {:source-paths ["src"]
     :compiler {:output-to "target/synergize.js"
                :optimizations :whitespace
                :pretty-print true}}}})
