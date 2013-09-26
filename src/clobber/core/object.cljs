(ns clobber.core.object
  (:require [clobber.util :refer [strkey obj-only]]
            [goog.object :as gobject]))

(extend-type object
  ILookup
  (-lookup
    ([o k]
       (aget o (strkey k)))
    ([o k not-found]
      (let [s (strkey k)]
        (if (gobject/containsKey o s)
          (aget o s)
          not-found))))

  IEmptyableCollection
  (-empty [o]
    (obj-only o :empty)
    (js-obj))

  ICounted
  (-count [o]
    (obj-only o :count)
    (.-length (js-keys o)))

  IAssociative
  (-assoc [o k v]
    (obj-only o :assoc)
    (conj o [k v]))

  ITransientCollection
  (-conj! [o [k v]]
    (assoc! o k v))
  (-persistent! [o]
    (obj-only o :persistent!)
    (into {} (map (fn [[k v]] [(keyword k) v]) o)))

  ITransientAssociative
  (-assoc! [o k v]
    (aset o (strkey k) v)
    o)

  ITransientMap
  (-dissoc! [o k]
    (gobject/remove o (strkey k))
    o)

  ISeqable
  (-seq [o]
    (obj-only o :seq)
    (map (fn [k] [k (get o k)]) (js-keys o))))
