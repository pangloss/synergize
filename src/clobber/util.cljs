(ns synergize.util)

(defn strkey [x]
  (cond
    (string? x) x
    (keyword? x) (name x)
    :else (str x)))

(defn ->array [obj]
  (.call js/Array.prototype.slice obj))
