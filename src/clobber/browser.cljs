(ns clobber.browser
  (:require clobber.browser.node-list))

; Enable print / println / pr / prn in the browser.
(set! *print-fn* #(js/console.log %))

(set! pr (fn
           ([                   ] (js/console.log                    ))
           ([a                  ] (js/console.log a                  ))
           ([a b                ] (js/console.log a b                ))
           ([a b c              ] (js/console.log a b c              ))
           ([a b c d            ] (js/console.log a b c d            ))
           ([a b c d e          ] (js/console.log a b c d e          ))
           ([a b c d e f        ] (js/console.log a b c d e f        ))
           ([a b c d e f g      ] (js/console.log a b c d e f g      ))
           ([a b c d e f g h    ] (js/console.log a b c d e f g h    ))
           ([a b c d e f g h i  ] (js/console.log a b c d e f g h i  ))
           ([a b c d e f g h i j] (js/console.log a b c d e f g h i j))))
(set! prn pr)

