(ns pets-api.pets.repository
  (:require [korma.core :as k]
            [schema.core :as s]
            [pets-api.pets.pet :refer [Pet]]))

(k/defentity pets)

(s/defn get-all :- [Pet]
  []
  (k/select pets))

(s/defn get-by-id [id :- s/Str]
  (first
   (k/select pets
             (k/where {:id [= id]}))))

(s/defn save [pet :- Pet]
  (k/insert pets
            (k/values {:id (:id pet)
                       :name (:name pet)
                       :race (:race pet)
                       :age (:age pet)
                       :owner (:id (:owner pet))})))

(s/defn delete [id :- s/Str]
  (k/delete pets
            (k/where {:id [= (.toString id)]})))

(s/defn edit [id :- s/Str, pet :- Pet]
  (k/update pets
            (k/set-fields {:name (:name pet)
                           :race (:race pet)
                           :age (:age pet)
                           :owner (:id (:owner pet))})
            (k/where {:id [= id]})))

(s/defn limpa-tabela []
  (k/delete pets))




