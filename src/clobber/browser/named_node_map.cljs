(ns clobber.browser.named-node-map
  (:require [clobber.util :refer [->array]]))

(when js/window.NamedNodeMap
  (extend-type js/NamedNodeMap
    cljs.core/ISeqable
    (-seq [nodes]
      (seq (->array nodes)))

    ILookup
    (-lookup
      ([nodes k]
       (when-let [attr (.getNamedItem nodes (name k))]
         (.-value attr)))
      ([nodes k not-found]
       (if-let [attr (.getNamedItem nodes (name k))]
         (.-value attr)
         not-found)))

    ICounted
    (-count [nodes]
      (.-length nodes))))

