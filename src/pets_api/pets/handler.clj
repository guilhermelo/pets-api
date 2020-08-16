(ns pets-api.pets.handler
  (:require [ring.util.http-response :refer [ok]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            ;; [metrics.core :refer [default-registry]]
            ;; [metrics.ring.expose :refer [render-metrics]]
            [pets-api.pets.service :as service]
            [pets-api.pets.pet :refer [Pet]]
            [schema.core :as s]))

(def pet-routes
  [(GET "/" []
     :return [Pet]
     :summary "Return all pets"
     (ok (service/get-all)))

   (POST "/" []
     :return {:result Pet}
     :body [pet Pet]
     :summary "Add a new Pet"
     (ok {:result (service/save (:body pet))}))

   (PUT "/:id" []
     :return {:result Pet}
     :body [pet Pet]
     :summary "Update a pet"
     (ok
       (let [id (:id pet)
             pet-encontrado (service/get-by-id id)]
         (if pet-encontrado
           (do
             (service/edit id pet)
             {:result pet})
           {:message "Pet não encontrado"}))))

   (DELETE "/:id" [id]
     :path-params [id :- s/Str]
     :summary "Delete a pet"
     (ok (let [pet (service/get-by-id id)]
           (if pet
             (do
               (service/delete id)
               {:result pet})
             {:message "Pet não encontrado"}))))])
