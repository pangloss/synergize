(ns clobber.core.array
  (:require [clobber.util :refer [strkey]]))

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
    a))
