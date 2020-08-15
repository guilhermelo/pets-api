(ns pets-api.customer.repository
  (:require [pets-api.database]
            [korma.core :as k]
            [schema.core :as s]
            [pets-api.customer.customer :refer [Customer]]
            [pets-api.util.uuid.uuid-generator :as uuid]))


(k/defentity customers)

(defn- dissociated [customer]
  (-> (assoc customer :monthly-payment (:monthly_payment customer))
      (dissoc :monthly_payment)))

(s/defn get-customers :- [Customer]
  []
  (let [saved-customers (k/select customers)]
    (map dissociated saved-customers)))

(s/defn save [customer :- Customer]
  (k/insert customers
            (k/values {:id (:id customer)
                       :name (:name customer)
                       :address (:address customer)
                       :document (:document customer)
                       :plan (name (:plan customer))
                       :monthly_payment (:monthly-payment customer)})))

(s/defn delete-customer [id :- s/Str]
  (k/delete customers
            (k/where {:id [= id]})))

(s/defn limpa-tabela []
  (k/delete customers))