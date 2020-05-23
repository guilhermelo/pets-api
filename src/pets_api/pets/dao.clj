(ns pets-api.pets.dao
  (:require [korma.core :as k]
            [pets-api.util.uuid.uuid-generator :as uuid]))

(k/defentity pets)

(defn get-all []
  (k/select pets))

(defn get-by-id [id]
  (first
    (k/select pets
            (k/where {:id [= id]}))))

(defn insere [pet]
  (k/insert pets
    (k/values {:id (uuid/generate-uuid)
             :nome (:nome pet)
             :bairro (:bairro pet)
             :rua (:rua pet)
             :numero (:numero pet)
             :dono (:dono pet)})))

(defn deleta [id]
  (k/delete pets
          (k/where {:id [= (.toString id)]})))

(defn atualiza [id pet]
  (k/update pets
          (k/set-fields {:nome (:nome pet)
                       :bairro (:bairro pet)
                       :rua (:rua pet)
                       :numero (:numero pet)
                       :dono (:dono pet)})
          (k/where {:id [= id]})))




