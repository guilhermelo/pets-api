(ns pets-api.customer.handler
  (:require [ring.util.http-response :refer [ok]]
            [compojure.api.sweet :refer [GET POST]]
            [pets-api.customer.service :as service]
            [pets-api.customer.customer :refer [Customer]]))

(def customer-routes
  [(GET "/" []
     :return [Customer]
     :summary "Return all customers"
     (ok (service/get-all)))

   (POST "/" []
     :return {:result Customer}
     :body [customer Customer]
     :summary "Add a new customer"
     (service/save customer)
     (ok {:result customer}))])
