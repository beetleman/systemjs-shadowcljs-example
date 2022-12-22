(ns acme.app
  (:require [reagent.dom :as rdom]
            [reagent.core :as reagent]))

(defn hello-world [{:keys [user-name]}]
  [:div
   [:h1 "Hello " user-name "!"]])

(defn mount [el state]
  (rdom/render [hello-world @state] el))

(defonce state (reagent/atom {:user-name ""}))
(defonce root-el (atom nil))

(defn ^:dev/after-load on-reload []
  (when-let [el @root-el]
    (mount el state)))

(defn ^:export init [opts]
  (let [{:keys [root userName] :as x} (js->clj opts :keywordize-keys true)]
    (reset! state {:user-name userName})
    (reset! root-el root)
    (mount @root-el state)))
