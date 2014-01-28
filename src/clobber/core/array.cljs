(ns clobber.core.array
  (:require [clobber.util :refer [strkey]]))

(defn extend-array
  "Extend javascript arrays with transient associative methods."
  [obj]
  (specify! obj
    IEmptyableCollection
    (-empty [a]
      (array))

    ITransientCollection
    (-conj! [a x]
      (.push a x)
      a)
    (-persistent! [a]
      (into [] a))

    ITransientAssociative
    (-assoc! [a k v]
      (aset a (strkey k) v)
      a)))
