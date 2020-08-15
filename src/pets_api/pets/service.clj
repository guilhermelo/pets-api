(ns pets-api.pets.service
  (:require [pets-api.pets.repository :as repository]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]))

(defn get-all []
  (repository/get-all))

(defn get-by-id [id]
  (repository/get-by-id id))

(defn insert [pet]
  (repository/insert-pet (assoc pet :id (generate-uuid))))

(defn update [id pet]
  (repository/atualiza id pet))

(defn delete [id]
  (repository/deleta id))

