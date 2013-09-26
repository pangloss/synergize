(ns clobber.core.object.coll
  (:require [clobber.util :refer [obj-only]]
            [goog.object :as gobject]))

; This ns is not included by default because
; it will cause coll? to return true for any
; object.

(extend-type object
  ICollection
  (-conj [parent [k v]]
    (obj-only parent :conj)
    (let [o (js-obj)]
      (assoc! o k v)
      (gobject/extend o parent)
      o)))

