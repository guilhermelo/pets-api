(ns pets-api.handler
  (:require [compojure.api.sweet :refer [routes context api undocumented]]
            [metrics.ring.instrument :refer [instrument]]
            [compojure.route :as router]
            [pets-api.pets.handler :refer [pet-routes]]
            [pets-api.users.handler :refer [user-routes]]
            [pets-api.login.handler :refer [login-routes]]
            [pets-api.customer.handler :refer [customer-routes]]
            [pets-api.metrics.handler :refer [metrics-routes]]
            [pets-api.login.middleware.auth-middleware :refer [token-auth]]))

(def app
  (instrument
    (api
      {:swagger
       {:ui "/swagger-ui"
        :spec "/swagger.json"
        :data {
               :info {:version "1.0.0"
                      :title "Pets API"
                      :description "Pets API for delivery data for apps and single page applications"}
               :tags [{:name "PetsAPI", :description "APIs"}]
               :securityDefinitions {:api_key {:type "apiKey" :name "Authorization" :in "header"}}}}}

      (context "/api" []
       (context "/login" []
         :tags ["login"]
         (apply routes login-routes))

       (context "/pets" []
         :tags ["pets"]
         :middleware [token-auth]
         (apply routes pet-routes))

       (context "/users" []
         :tags ["users"]
        ;;  :middleware [token-auth]
         (apply routes user-routes))
        
        (context "/customers" []
          :tags ["customers"]
          :middleware [token-auth]
          (apply routes customer-routes))

       (context "/metrics" []
         :tags ["metrics"]
         :middleware [token-auth]
         (apply routes metrics-routes)))

      (undocumented (router/not-found "Rota não encontrada :(")))))