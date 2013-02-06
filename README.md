# node-webkit-cljs

Simple wrapper for [node-webkit](https://github.com/rogerwang/node-webkit) native APIs.
Provides idiomatic approach for most use cases.

**Status**: many APIs (i.e. Shell, Clipboard, File dialogs) are missing and planned for next releases. Contribution is highly appreciated.

Add `[node-webkit-cljs "0.1.1"]` to lein `:dependencies`.

``` clojure
(ns myapp.core
  :require [node-webkit-cljs :as nw])

(nw/tray! {:title "My App"
           :icon "img/icon.png"
           :menu (nw/menu [{:label "Open..." :click #(.show (nw/window))}
                           {:type "separator"}
                           {:label "Quit" :click nw/quit}])})

(.on (nw/window) "close" #(.hide (nw/window)))

; and so on

```


## Documentation

### [API documentation](http://flamefork.github.com/node-webkit-cljs/)

[Official node-webkit Native UI API documentation](https://github.com/rogerwang/node-webkit/wiki/Native-UI-API-Manual).

## License

Copyright © 2013 Ilia Ablamonov

Distributed under the Eclipse Public License, the same as Clojure.
