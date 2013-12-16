(ns clobber.browser.style-sheet-list
  (:require [clobber.util :refer [->array]]))

(defn clobber
  "These extensions make it much easier to work with the DOM from ClojureScript
   by making lists of nodes returned by DOM query methods act like regular
   sequences."
  []
  (extend-type js/StyleSheetList
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
      (.-length nodes))))
