(ns future-app-rum.android.components
  (:require-macros [rum.core :refer [defc]])
  (:require [re-natal.support :as support]
            [rum.core :as rum]))

(def ReactNative (js/require "react-native"))
(def MaterialDesign (js/require "react-native-material-design"))

(defn create-element [rn-comp opts & children]
      (apply js/React.createElement rn-comp (clj->js opts) children))

(def Card (partial create-element (.-Card MaterialDesign)))
(def CardBody (partial create-element (.. MaterialDesign -Card -Body)))
(def CardMedia (partial create-element (.. MaterialDesign -Card -Media)))
(def app-registry (.-AppRegistry ReactNative))
(def view (partial create-element (.-View ReactNative)))
(def text (partial create-element (.-Text ReactNative)))
(def image (partial create-element (.-Image ReactNative)))
(def touchable-highlight (partial create-element (.-TouchableHighlight ReactNative)))
(def modal (partial create-element (.-Modal ReactNative)))

(defn alert [title]
      (.alert (.-Alert ReactNative) title))
