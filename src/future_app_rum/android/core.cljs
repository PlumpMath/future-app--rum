(ns future-app-rum.android.core
  (:require-macros [rum.core :refer [defc]])
  (:require [re-natal.support :as support]
            [future-app-rum.android.components :refer
             [Card CardBody CardMedia view text image
              touchable-highlight modal app-registry]]
            [rum.core :as rum]))

(set! js/window.React (js/require "react"))

(def logo-img (js/require "./images/cljs.png"))

(defc modal-me < rum/cursored-watch [state]
  (view {}
        (modal {} (view {} (text {} (:greeting state))))))
(defonce app-state (atom {:greeting "Hello Clojure in iOS and Android!"}))

(defc AppRoot < rum/cursored-watch [state]
          (view {:style {:flexDirection "column" :margin 40 :alignItems "center"}}
                (text {:style {:fontSize 30 :fontWeight "100"
                               :marginBottom 20 :textAlign "center"}}
                      (:greeting @state))
                (Card {:style {:width 300}}
                      (CardMedia {:image (image {:source logo-img
                                                 ;:style  {:width 80 :height 80 :marginBottom 30}
                                                 })})
                      (CardBody {} (text {} "whacky")))
                (image {:source logo-img
                        :style  {:width 80 :height 80 :marginBottom 30}})
                (touchable-highlight {:style   {:backgroundColor "#999"
                                                :padding 10 :borderRadius 5}
                                      :onPress #(js/alert "whooo")}
                                     (text {:style {:color "white" :textAlign
                                                    "center" :fontWeight "bold"}}
                                           "PRESS ME"))))

(defonce root-component-factory (support/make-root-component-factory))

(defn mount-app [] (support/mount (AppRoot app-state)))

(defn init []
      (mount-app)
  (.registerComponent app-registry "FutureAppRum"
                      (fn [] root-component-factory)))
