(ns clobber.browser.node-list)

(extend-type js/NodeList
  cljs.core/ISeqable
  (-seq [nodes]
    (seq (.call js/Array.prototype.slice nodes))))
