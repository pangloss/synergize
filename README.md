# clobber

A ClojureScript library that extends JavaScript objects with
ClojureScript core protocols.

## Usage

Only require the namespaces corresponding to the objects you wish to
extend.  If you wish to extend all objects, require the top-level
namespaces `clobber.core` and `clobber.browser`.  Alternatively, you can
extend only specific objects: just require their corresponding
namespaces (ie. `clobber.browser.node-list`).

No new protocols are defined in this library. Only simple methods that
minimally implement the necessary protocols are present.  Because this
only extends core protocols, there are no methods to refer to from
this library.

Because of the way ClojureScript works, if you require these namespaces
anywhere in your project, the effected objects will be extended throughout
your project.

```clojure
(ns your.project
  (:require clobber.core clobber.browser))

; Now you can natively work with 
(doseq [element js/document.body.childNodes]
  (.log js/console "Do something with" element))
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
