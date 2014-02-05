(ns synergize.browser
  (:require [synergize.util :refer [->array]]))

(defmulti synergize! type)


(defn pr* [& args]
  (let [c js/console]
    (.apply (.-log c) c (into-array args))))

(defn enable-print
  "Enable print / println / pr / prn in the browser."
  []
  (set! *print-fn* #(js/console.log %))
  (set! pr pr*)
  (set! prn pr*))


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

(defmethod synergize! js/CSSRuleList [obj] (css-rule-list obj))


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


(defmethod synergize! js/CSSStyleDeclaration [obj] (css-style-declaration obj))


(defn node-list
  "These extensions make it much easier to work with the DOM from ClojureScript
   by making this list of nodes act like a regular sequence."
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

(def dom-collection node-list)

(defmethod synergize! js/NodeList [obj] (node-list obj))
(defmethod synergize! js/DOMCollection [obj] (node-list obj))


(defn named-node-map
  "These extensions make the result of calling .attributes act like a read-only
   map for lookup and like an array of attributes for seq. The combination is
   odd... but that's how NamedNodeMap rolls, and I've found this to work very well.."
  [obj]
  (specify! obj
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
      (.-length nodes))))

(defmethod synergize! js/NamedNodeMap [obj] (named-node-map obj))


(defn style-sheet-list
  "These extensions make it much easier to work with the DOM from ClojureScript
   by making lists of nodes returned by DOM query methods act like regular
   sequences."
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

(defmethod synergize! js/StyleSheetList [obj] (style-sheet-list obj))
