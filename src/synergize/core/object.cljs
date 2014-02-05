(ns synergize.core.object
  (:require [synergize.util :refer [strkey]]
            [goog.object :as gobject]))

; Intentionally not tied into the synergize! multimethod
(defn synergize-object!
  "Enable collection protocols on a regular javascript object."
  [obj]
  (specify! obj
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
      (js-obj))

    ICounted
    (-count [o]
      (.-length (js-keys o)))

    IAssociative
    (-assoc [o k v]
      (conj o [k v]))

    ITransientCollection
    (-conj! [o [k v]]
      (assoc! o k v))
    (-persistent! [o]
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
      (map (fn [k] [k (get o k)]) (js-keys o)))

    IMap
    (-dissoc [parent k]
      (let [o (js-obj)]
        (gobject/extend o parent)
        (dissoc! o k)))

    ICollection
    (-conj [parent [k v]]
      (let [o (js-obj)]
        (assoc! o k v)
        (gobject/extend o parent)
        o))))
