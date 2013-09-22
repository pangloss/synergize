(ns clobber.util)

(defn strkey [x]
  (cond
    (string? x) x
    (keyword? x) (name x)
    :else (str x)))

(defn obj-name [this]
  (if-let [[_ n] (re-find #"^function (\w+)" (str this))]
    n
    "Object"))

;; Everything in JS inherits from js/Object. If we applied some of these
;; methods to random JS classes, we would get very odd results. For
;; instance, (count (js/Date.)) is 4
(defn obj-only [o method]
  (when-not (identical? (type o) js/Object)
    (throw (js/TypeError. (str (obj-name (type o)) " does not implement '" (name method) "'")))))

(defn ->array [obj]
  (.call js/Array.prototype.slice obj))
