(ns pets-api.users.dao
  (:require [pets-api.database]
            [korma.core :refer :all]
            [nano-id.core :refer :all]
            [schema.core :as s]
            [pets-api.users.user :refer [User]]
            [crypto.password.bcrypt :as bcrypt]))

(defentity users)

(s/defn get-all :- [User]
  []
  (select users))

(s/defn get-by-id [id :- s/Str]
  (first
    (select users
            (where {:id [= id]}))))

(s/defn insere [user :- User]
  (insert users
          (values {:id (nano-id 15)
                   :nome (:nome user)
                   :email (:email user)
                   :username (:username user)
                   :password (bcrypt/encrypt (:password user))})))

(s/defn deleta [id :- s/Str]
  (delete users
          (where {:id [= id]})))

(s/defn atualiza [id :- s/Str, user :- User]
  (update users
          (set-fields {:id (:id user)
                       :nome (:nome user)
                       :email (:email user)
                       :username (:username user)
                       :password (:password user)})
          (where {:id [= id]})))