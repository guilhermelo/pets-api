(ns pets-api.dao.pets
  (:require [pets-api.database]
            [korma.core :refer :all]
            [nano-id.core :refer :all]))

(defentity pets)

(defn get-todos []
  (select pets))

(defn get-by-id [id]
  (first
    (select pets
            (where {:id [= id]}))))

(defn insere [pet]
  (insert pets
    (values {:id (nano-id 15)
             :nome (:nome pet)
             :bairro (:bairro pet)
             :rua (:rua pet)
             :numero (:numero pet)
             :dono (:dono pet)})))

(defn deleta [id]
  (delete pets
          (where {:id [= (.toString id)]})))

(defn atualiza [id pet]
  (update pets
          (set-fields {:nome (:nome pet)
                       :bairro (:bairro pet)
                       :rua (:rua pet)
                       :numero (:numero pet)
                       :dono (:dono pet)})
          (where {:id [= id]})))




