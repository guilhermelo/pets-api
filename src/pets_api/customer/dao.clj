(ns pets-api.customer.dao
  (:require [pets-api.database]
            [korma.core :as k]
            [schema.core :as s]
            [pets-api.customer.customer :refer [Customer]]
            [pets-api.util.uuid.uuid-generator :as uuid]))


(k/defentity customers)

(defn- dissociated [customer]
  (let [assoc-customer (assoc customer :monthly-payment (:monthly_payment customer))]
    (dissoc assoc-customer :monthly_payment)))

(s/defn get-customers :- [Customer]
        []
        (let [saved-customers (k/select customers)]
          (map dissociated saved-customers)))

(s/defn save [customer :- Customer]
    (k/insert customers 
              (k/values {:id (uuid/generate-uuid)
                         :name (:name customer)
                         :address (:address customer)
                         :document (:document customer)
                         :plan (name (:plan customer))
                         :monthly_payment (:monthly-payment customer)})))