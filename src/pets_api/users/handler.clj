(ns pets-api.users.handler
  (:require [ring.util.http-response :refer [ok]]
            [compojure.api.sweet :refer [GET POST DELETE PUT]]
            [pets-api.users.dao :as dao]
            [pets-api.users.user :refer [User]]
            [schema.core :as s]))

(def user-routes
  [(GET "/" []
     :return [User]
     :summary "Return all users"
     (dao/get-users))

   (POST "/" []
         :return {:result User}
         :body [user User]
         :summary "Add a new user"
     (ok {:result (dao/insert-user user)}))

   (PUT "/:id" []
     :path-params [id :- s/Str]
     :body [user User]
     :summary "Update the user"
     (ok
       (let [user-encontrado (dao/get-user-by-id id)
             user-atualizado user]
         (if user-encontrado
           (do
             (dao/update-user id user-atualizado)
             {:result user-atualizado})
           {:message "User not found"}))))

   (DELETE "/:id" []
     :path-params [id :- s/Str]
     :summary "Delete the user"
     (ok (let [user (dao/get-user-by-id id)]
           (if user
             (do
               (dao/delete-user id)
               {:result user})
             {:message "User not found"}))))])


