(ns pets-api.users.dao
  (:require [pets-api.database]
            [korma.core :refer :all]
            [nano-id.core :refer :all]))

(defentity users)

(defn get-all []
  (select users))

(defn get-by-id [id]
  (first
    (select users
            (where {:id [= id]}))))

(defn insere [user]
  (insert user
          (values {:id (nano-id 15)
                   :nome (:nome user)
                   :email (:email user)
                   :username (:username user)
                   :password (:password user)})))

(defn deleta [id]
  (delete users
          (where {:id [= (.toString id)]})))

(defn atualiza [id user]
  (update users
          (set-fields {:id (nano-id 15)
                       :nome (:nome user)
                       :email (:email user)
                       :username (:username user)
                       :password (:password user)})
          (where {:id [= id]})))