(ns clobber.browser.node-list
  (:require [clobber.util :refer [->array]]))

(extend-type js/NodeList
  cljs.core/ISeqable
  (-seq [nodes]
    (seq (->array nodes)))

  ILookup
  (-lookup
    ([nodes k]
     (aget nodes k))
    ([nodes k not-found]
     (if-let [v (aget nodes k)]
       v
       not-found)))

  ICounted
  (-count [nodes]
    (.-length nodes)))
