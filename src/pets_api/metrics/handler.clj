(ns pets-api.metrics.handler
  (:require [ring.util.http-response :refer [ok not-found created]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [metrics.core :refer [default-registry]]
            [metrics.ring.expose :refer [render-metrics]]))

(def metrics-routes
  [(GET "/" []
     :summary "Application metrics"
     (ok (render-metrics default-registry)))])
