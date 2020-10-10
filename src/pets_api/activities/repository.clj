(ns pets-api.activities.repository
  (:require [korma.core :as k]
            [schema.core :as s]
            [pets-api.activities.activity :refer [Activity]]
            [java-time :as t]))

(k/defentity activities)

(s/defn get-by-pet :- [Activity]
  [pet-id :- s/Str]
  (k/select activities
            (k/where {:id [= pet-id]})))

(s/defn save [activity :- Activity]
  (k/insert activities
            (k/values {:id (:id activity)
                       :description (:description activity)
                       :date (t/format (t/formatter "yyyy-MM-dd HH:mm:ss") (:date activity))
                       :type (name (:type activity))
                       :pet (:id (:pet activity))})))

(s/defn limpa-tabela []
        (k/delete activities))