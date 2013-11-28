(ns clobber.browser
  (:require clobber.browser.node-list
            clobber.browser.named-node-map))

(defn pr* [& args]
  (let [c js/console]
    (.apply (.-log c) c (into-array args))))

(defn enable-print
  "Enable print / println / pr / prn in the browser."
  []
  (set! *print-fn* #(js/console.log %))
  (set! pr pr*)
  (set! prn pr*))

(defn clobber []
  (enable-print)
  (clobber.browser.node-list/clobber)
  (clobber.browser.named-node-map/clobber))
