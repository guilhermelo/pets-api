(ns pets-api.users.handler-test
  (:require [midje.sweet :refer [facts fact => against-background]]
            [ring.mock.request :as mock]
            [pets-api.handler :refer [app]]
            [pets-api.users.service :as service]))

(facts "Faz chamadas para a rota /users"
       (against-background (service/get-all) => '())
       (let [users '({:id "e64c3d2c-7fbf-41d5-861f-c1462cdb0935"
                      :name "João"
                      :email "joao@gmail.com"
                      :username "joao"
                      :password "joao123"})
             response (app (mock/request :get "/api/users"))]

         (fact "Deve retornar um json com um usuário"
               (:body response) => users)))