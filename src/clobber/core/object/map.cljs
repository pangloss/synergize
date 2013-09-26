(ns clobber.core.object.map
  (:require [clobber.util :refer [obj-only]]
            [goog.object :as gobject]))

; This ns is not included by default because
; it will cause map? to return true for any
; object.

(extend-type object
  IMap
  (-dissoc [parent k]
    (obj-only parent :dissoc)
    (let [o (js-obj)]
      (gobject/extend o parent)
      (dissoc! o k))))
