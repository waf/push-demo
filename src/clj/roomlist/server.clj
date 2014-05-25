(ns roomlist.server
  (:require [org.httpkit.server :as server]
            [ring.util.response :as response]
            [clojure.tools.logging :as log]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [roomlist.push :as push])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] (response/resource-response "public/index.html"))
  (GET  "/channel" req (push/ring-ws-handoff req))
  (POST "/channel" req (push/ring-ws-post req))
  (route/resources "/")
  (route/not-found "404! :("))

(defn- wrap-request-logging [handler]
  (fn [{:keys [request-method uri] :as req}]
    (let [resp (handler req)]
      (log/info (name request-method) (:status resp)
                (if-let [qs (:query-string req)]
                  (str uri "?" qs) uri))
      resp)))

(def app 
  (-> app-routes
      (handler/site)
      (wrap-request-logging)))

(defn -main [& args]
  (log/info "creating db")
  (let [db (push/create-db "datomic:mem://roomlist")]
    (log/info "starting router")
    (push/ws-message-router (:db-connection db))
    (log/info "starting change monitor")
    (future (push/change-monitor (:change-queue db))))
  (log/info "starting server")
  (server/run-server app {:port 3000})
  (log/info "server started. http://localhost:3000"))
