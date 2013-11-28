(ns clobber.core.array
  (:require [clobber.util :refer [strkey]]))

(defn clobber
  "Extend javascript arrays with transient associative methods."
  []
  (extend-type array
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
