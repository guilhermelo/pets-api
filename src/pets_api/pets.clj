(ns pets-api.pets
  (:require [pets-api.fake-db :as db]))

(defn pets []
  (db/get-pets))

(defn insere [pet]
  (db/insere-pet! pet))

(defn atualiza [id pet]
  (db/atualiza-pet! id pet))

(defn deleta [id]
  (db/deleta-pet! id))