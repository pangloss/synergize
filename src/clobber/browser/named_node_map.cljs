(ns clobber.browser.named-node-map
  (:require [clobber.util :refer [->array]]))

(defn clobber
  "These extensions make the result of calling .attributes act like a read-only
   map for lookup and like an array of attributes for seq. The combination is
  odd... but that's how NamedNodeMap rolls, and I've found this to work very well.."
  []
  (when js/NamedNodeMap
    (extend-type js/NamedNodeMap
      cljs.core/ISeqable
      (-seq [nodes]
        (seq (->array nodes)))

      ILookup
      (-lookup
        ([nodes k]
         (when-let [attr (.getNamedItem nodes (name k))]
           (.-value attr)))
        ([nodes k not-found]
         (if-let [attr (.getNamedItem nodes (name k))]
           (.-value attr)
           not-found)))

      ITransientMap
      (-dissoc! [nodes k]
        (when (.getNamedItem nodes (name k))
          (.removeNamedItem nodes (name k)))
        nodes)

      ICounted
      (-count [nodes]
        (.-length nodes)))))

