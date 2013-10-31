(ns clobber.browser
  (:require clobber.browser.node-list
            clobber.browser.named-node-map))

; Enable print / println / pr / prn in the browser.
(set! *print-fn* #(js/console.log %))

(defn pr* [& args]
  (let [c js/console]
    (.apply (.-log c) c (into-array args))))

(set! pr pr*)
(set! prn pr*)

