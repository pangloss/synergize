(ns clobber.browser.css-rule-list
  (:require [clobber.util :refer [->array]]))

(defn css-rule-list
  "These extensions make it much easier to work with the DOM from ClojureScript
   by making the given list of nodes returned by DOM query methods act
   like a regular sequence."
  [obj]
  (specify! obj
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
