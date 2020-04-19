(ns pets-api.users.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [pets-api.users.dao :as dao]
            [pets-api.users.user :refer [User]]
            [schema.core :as s]))

(def user-routes
  [(GET "/users" []
     :return [User]
     :summary "Return all users"
     (dao/get-all))

   (POST "/users" []
         :return {:result User}
         :body [user User]
         :summary "Add a new user"
     (ok {:result (dao/insere user)}))

   (PUT "/users/:id" []
     :path-params [id :- s/Str]
     :body [user User]
     :summary "Update the user"
     (ok
       (let [user-encontrado (dao/get-by-id id)
             user-atualizado user]
         (if user-encontrado
           (do
             (dao/atualiza id user-atualizado)
             {:result user-atualizado})
           {:message "User not found"}))))

   (DELETE "/users/:id" []
     :path-params [id :- s/Str]
     :summary "Delete the user"
     (ok (let [user (dao/get-by-id id)]
           (if user
             (do
               (dao/deleta id)
               {:result user})
             {:message "User not found"}))))])


