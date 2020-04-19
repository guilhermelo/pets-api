(ns pets-api.pets.handler
  (:require [ring.util.http-response :refer [ok not-found created]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [metrics.core :refer [default-registry]]
            [metrics.ring.expose :refer [render-metrics]]
            [pets-api.pets.dao :as dao]
            [pets-api.pets.pet :refer [Pet]]
            [schema.core :as s]))

(def pet-routes
  [(GET "/metrics" []
     :summary "Application metrics"
     (ok (render-metrics default-registry)))

   (GET "/pets" []
     :return [Pet]
     :summary "Return all pets"
     (ok (dao/get-all)))

   (POST "/pets" []
     :return {:result Pet}
     :body [pet Pet]
     :summary "Add a new Pet"
     (ok {:result (dao/insere (:body pet))}))

   (PUT "/pets/:id" []
     :return {:result Pet}
     :body [pet Pet]
     :summary "Update a pet"
     (ok
       (let [id (:id pet)
             pet-encontrado (dao/get-by-id id)]
         (if pet-encontrado
           (do
             (dao/atualiza id pet)
             {:result pet})
           {:message "Pet não encontrado"}))))

   (DELETE "/pets/:id" [id]
     :path-params [id :- s/Str]
     :summary "Delete a pet"
     (ok (let [pet (dao/get-by-id id)]
           (if pet
             (do
               (dao/deleta id)
               {:result pet})
             {:message "Pet não encontrado"}))))])
