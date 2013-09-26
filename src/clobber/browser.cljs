(ns clobber.browser
  (:require clobber.browser.node-list))

; Enable print / println / pr / prn in the browser.
(set! *print-fn* #(js/console.log %))
