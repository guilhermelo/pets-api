(ns pets-api.customer.service
  (:require [pets-api.customer.dao :as dao]
            [schema.core :as s]
            [pets-api.customer.customer :refer [Customer]]))

(defn get-customers []
  (dao/get-customers))

(s/defn save [customer :- Customer]
  (dao/save customer))