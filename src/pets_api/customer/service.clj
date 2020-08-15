(ns pets-api.customer.service
  (:require [schema.core :as s]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]
            [pets-api.customer.repository :as repository]
            [pets-api.customer.customer :refer [Customer]]))

(defn get-customers [] :- [Customer]
  (repository/get-customers))

(s/defn save [customer :- Customer]
  (repository/save (assoc customer :id (generate-uuid))))

(s/defn delete [id :- s/Str]
  (repository/delete-customer id))