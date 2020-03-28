(ns pets-api.pets.handler
  (:require [ring.util.http-response :refer [ok not-found created]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [metrics.core :refer [default-registry]]
            [metrics.ring.expose :refer [render-metrics]]
            [pets-api.pets.dao :as dao]))


(def pet-routes
  [(GET "/metrics" []
     :summary "Application metrics"
     (ok (render-metrics default-registry)))

   (GET "/pets" []
     (ok {:result (dao/get-all)}))

   (POST "/pets" request
     (ok {:result (dao/insere (:body request))}))

   (PUT "/pets/:id" request
     (ok
       (let [id (get-in request [:params :id])
             pet-encontrado (dao/get-by-id id)
             pet-atualizado (:body request)]
         (if pet-encontrado
           (do
             (dao/atualiza id pet-atualizado)
             {:result pet-atualizado})
           {:message "Pet não encontrado"}))))

   (DELETE "/pets/:id" [id]
     (ok (let [pet (dao/get-by-id id)]
           (if pet
             (do
               (dao/deleta id)
               {:result pet})
             {:message "Pet não encontrado"}))))])
