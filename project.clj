(defproject clobber "0.1.0-SNAPSHOT"
  :clojurescript? true
  :description "Clobber Javascript objects with lots of useful stuff"
  :url "https://github.com/pangloss/clobber"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[lein-cljsbuild "0.3.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild
  {:builds
   {:dev
    {:source-paths ["src"]
     :compiler {:output-to "target/clobber.js"
                :optimizations :whitespace
                :pretty-print true}}}})
