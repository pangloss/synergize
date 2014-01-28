(ns clobber.browser.css-style-declaration
  (:require [clobber.util :refer [->array]]))

(defn css-style-declaration
  "Enable use of get, assoc! and dissoc! with the object
   returned by .-style or .getComputedStyle"
  [obj]
  (specify! obj
    ILookup
    (-lookup
      ([csd k]
       (.getPropertyValue csd (name k)))
      ([csd k not-found]
       (if-let [v (.getPropertyValue csd (name k))]
         v
         not-found)))

    ITransientAssociative
    (-assoc! [csd k v]
      (.setProperty csd (name k) v)
      csd)

    ITransientMap
    (-dissoc! [csd k]
      (.removeProperty csd (name k))
      csd)))
