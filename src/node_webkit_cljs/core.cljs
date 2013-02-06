;; This is only a thin wrapper for node-webkit Native UI API. Generally, node-webkit's
;; [Native UI API Manual](https://github.com/rogerwang/node-webkit/wiki/Native-UI-API-Manual)
;; is a good place to start learning how to use this APIs.

(ns node-webkit-cljs.core)

(def ^:private gui (js/require "nw.gui"))

;; ## Window

(defn window
  "If `window-object` is not specifed, then return current window's Window object,
  otherwise return `window-object`s Window object."
  ([] (.Window.get gui))
  ([window-object] (.Window.get gui window-object)))

;; ## Menu

(defn- append-menuitems [menu items]
  (let [ctor (.-MenuItem gui)]
    (doseq [item-options items]
      (.append menu (new ctor (clj->js item-options))))))

(defn menu
  "Create a new Menu. Items is a vector of MenuItem options.
  Options can have following fields: `label`, `icon`, `tooltip`, `type`, `click`, `checked`, `enabled` and `submenu`.
  See [MenuItem documentation](https://github.com/rogerwang/node-webkit/wiki/MenuItem)."
  [items]
  (let [ctor (.-Menu gui)]
    (doto (new ctor)
      (append-menuitems items))))

(defn menubar!
  "Create and set main Window's main Menu. Items is a vector of MenuItem options.
  Options can have following fields: `label`, `icon`, `tooltip`, `type`, `click`, `checked`, `enabled` and `submenu`.
  See [MenuItem documentation](https://github.com/rogerwang/node-webkit/wiki/MenuItem)."
  [items]
  (let [ctor (.-Menu gui)
        menu (new ctor (js-obj "type" "menubar"))]
    (append-menuitems menu items)
    (set! (.-menu (window)) menu)))

;; ## App

(defn argv
  "Get the command line arguments when starting the app."
  []
  (seq (.-App.argv gui)))

(defn quit
  "Quit current app. This method will not send close event
  to windows and app will just quit quietly."
  []
  (.App.quit gui))

;; ## Tray

(def ^:private ^:dynamic current-tray)

(defn tray!
  "Create a new Tray, options is a map contains initial settings for the Tray.
  `options` can have following fields: `title`, `tooltip`, `icon` and `menu`.
  See [Tray documentation](https://github.com/rogerwang/node-webkit/wiki/Tray)."
  [options]
  (when current-tray (.remove current-tray))
  (let [ctor (.-Tray gui)]
    (def current-tray (new ctor (clj->js options)))))

(defn update-tray
  "Assigns new value to one of the following options: `title`, `tooltip`, `icon` and `menu`.
  See [Tray documentation](https://github.com/rogerwang/node-webkit/wiki/Tray)."
  [option value]
  (aset current-tray (name option) value))
