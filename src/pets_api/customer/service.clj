(ns pets-api.customer.service
  (:require [schema.core :as s]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]
            [pets-api.customer.repository :as repository]
            [pets-api.customer.customer :refer [Customer]]))

(defn get-all [] :- [Customer]
  (repository/get-all))

(s/defn save [customer :- Customer]
  (repository/save (assoc customer :id (generate-uuid))))

(s/defn delete [id :- s/Str]
  (when (or (nil? id) (= "" id)) 
    (throw (ex-info "id is invalid" {:id id})))
  (repository/delete id))