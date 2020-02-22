(ns pets-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer :all]
            [pets-api.pets :refer :all]
            [cheshire.core :as json]
            [pets-api.dao.pets :as dao]))

(defn response [conteudo & [status]]
  {:status       (or status 200)
   :headers      {"Content-Type" "application/json; charset=utf-8"}
   :body         conteudo})

(defroutes app-routes
  (GET "/pets" []
    (response (dao/get-todos)))

  (POST "/pets" request
    (response (dao/insere (:body request))))

  (PUT "/pets/:id" request
    (response
        (let [id (get-in request [:params :id])
              pet-encontrado (dao/get-by-id id)
              pet-atualizado (:body request)]
               (if pet-encontrado
                  (do
                    (dao/atualiza id pet-atualizado)
                    pet-atualizado)
                  {:message "Pet não encontrado"}))))

  (DELETE "/pets/:id" [id]
    (response (let [pet (dao/get-by-id id)]
      (if pet
        (do
          (dao/deleta id)
          pet)
        {:message "Pet não encontrado"}))))

  (route/not-found "Rota não encontrada"))

(def app
  (-> (wrap-defaults app-routes api-defaults)
      (wrap-json-response)
      (wrap-json-body {:keywords? true})))
