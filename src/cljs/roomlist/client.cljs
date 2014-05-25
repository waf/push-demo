(ns roomlist.client
  (:require [reagent.core :as r]
            [roomlist.push :as push]))

(defn user-item [join-event]
  [:li (str (:user/name join-event) " joined at " (:db/txInstant join-event))])

(defn users-list []
  [:ul
   (for [join-event @push/events]
     [user-item join-event])])

(r/render-component 
  [users-list]
  (.getElementById js/document "entry-list"))
