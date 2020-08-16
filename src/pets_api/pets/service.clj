(ns pets-api.pets.service
  (:require [pets-api.pets.repository :as repository]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]))

(defn get-all []
  (repository/get-all))

(defn get-by-id [id]
  (repository/get-by-id id))

(defn save [pet]
  (repository/save (assoc pet :id (generate-uuid))))

(defn edit [id pet]
  (repository/edit id pet))

(defn delete [id]
  (repository/delete id))

