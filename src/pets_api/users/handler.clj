(ns pets-api.users.handler
  (:require [ring.util.http-response :refer [ok not-found created]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [pets-api.users.dao :as dao]))

(def user-routes
  [(GET "/users" []
     (dao/get-all))

   (POST "/users" request
     (ok {:result (dao/insere (:body request))}))

   (PUT "/users/:id" request
     (ok
       (let [id (get-in request [:params :id])
             user-encontrado (dao/get-by-id id)
             user-atualizado (:body request)]
         (if user-encontrado
           (do
             (dao/atualiza id user-atualizado)
             {:result user-atualizado})
           {:message "Usuário não encontrado"}))))

   (DELETE "/users/:id" [id]
     (ok (let [pet (dao/get-by-id id)]
           (if pet
             (do
               (dao/deleta id)
               {:result pet})
             {:message "Pet não encontrado"}))))])


