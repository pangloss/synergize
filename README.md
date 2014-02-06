# Synergize

A ClojureScript library that uses specify extends JavaScript object
instances with ClojureScript core protocols.

The current implementation is focussed primarily on DOM collection
objects.

Currently supported collection types:

* `NodeList` is returned by most DOM methods that produce
a collection of objects.
* `DOMCollection` is returned by other DOM methods for a collection of
  objects
* `NamedNodeMap` is returned by any node's `attributes` property
* `StyleSheetList`
* `CSSRuleList`
* `CSSStyleDeclaration`

## Usage

The `synergize!` multimethod will apply the correct protocol
specifications to any supported type.

```clojure
(ns your.project
  (:require [synergize.browser :refer [synergize!]]))

; Now you can natively work with 
(doseq [element (synergize! js/document.body.childNodes)]
  (.log js/console "Do something with" element))
```

## License

Copyright © 2013 Darrick Wiebe

Distributed under the Eclipse Public License, the same as Clojure.
