(ns clobber.browser)

(defn pr* [& args]
  (let [c js/console]
    (.apply (.-log c) c (into-array args))))

(defn enable-print
  "Enable print / println / pr / prn in the browser."
  []
  (set! *print-fn* #(js/console.log %))
  (set! pr pr*)
  (set! prn pr*))

