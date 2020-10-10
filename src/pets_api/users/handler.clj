(ns pets-api.users.handler
  (:require [ring.util.http-response :refer [ok]]
            [compojure.api.sweet :refer [GET POST DELETE PUT]]
            [pets-api.users.service :as service]
            [pets-api.users.user :refer [User]]))


(def user-routes
  [(GET "/" []
     :return [User]
     :summary "Return all users"
     (ok (service/get-all)))

   (POST "/" []
     :return {:result User}
     :body [user User]
     :summary "Add a new user"
     (ok {:result (service/save user)}))

   (PUT "/:id" []
     :path-params [id]
     :body [user User]
     :summary "Update the user"
     (ok
      (let [user-encontrado (service/get-user-by-id id)
            user-atualizado user]
        (if user-encontrado
          (do
            (service/edit id user-atualizado)
            {:result user-atualizado})
          {:message "User not found"}))))

   (DELETE "/:id" []
     :path-params [id]
     :summary "Delete the user"
     (ok (let [user (service/get-user-by-id id)]
           (if user
             (do
               (service/delete id)
               {:result user})
             {:message "User not found"}))))])


