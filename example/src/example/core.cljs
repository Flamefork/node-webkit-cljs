(ns example.core
    (:require [jayq.core :as jq]
              [node-webkit.core :as nw]))

(defn init []
  ; Example: making a tray menu
  (nw/tray! {:title "Example App"
             :menu (nw/menu [{:label "Show..." :click #(.show (nw/window))} ; Show window hidden by system close button
                             {:type "separator"}
                             {:label "Quit" :click nw/quit}])}) ; Really quit application

  ; Example: do not quit application on window close
  (.on (nw/window) "close" #(.hide (nw/window)))

  ; Example: use awesome jayq (jQuery wrapper) for DOM manipulation
  (jq/inner (jq/$ :#container) "Ready!"))

; Wait for DOM ready
(jq/document-ready init)
