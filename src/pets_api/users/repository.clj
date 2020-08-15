(ns pets-api.users.repository
  (:require [pets-api.database]
            [korma.core :as k]
            [schema.core :as s]
            [pets-api.users.user :refer [User]]
            [crypto.password.bcrypt :as bcrypt]))

(k/defentity users)

(s/defn get-users :- [User]
  []
  (k/select users))

(s/defn get-user-by-id [id :- s/Str]
  (first
   (k/select users
             (k/where {:id [= id]}))))

(s/defn get-user-by-username [username :- s/Str]
  (first (k/select users
                   (k/where {:username [= username]}))))

(s/defn insert-user [user :- User]
  (k/insert users
            (k/values {:id (:id user)
                       :name (:name user)
                       :email (:email user)
                       :username (:username user)
                       :password (bcrypt/encrypt (:password user))})))

(s/defn delete-user [id :- s/Str]
  (k/delete users
            (k/where {:id (.toString id)})))

(s/defn update-user [id :- s/Str, user :- User]
  (k/update users
            (k/set-fields {:id (:id user)
                           :name (:name user)
                           :email (:email user)
                           :username (:username user)
                           :password (:password user)})
            (k/where {:id [= id]})))

(s/defn limpa-tabela []
  (k/delete users))