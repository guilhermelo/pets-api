(ns pets-api.users.service
  (:require [schema.core :as s]
            [pets-api.users.repository :as repository]
            [pets-api.users.user :refer [User]]))

(s/defn get-all [] :- [User]
  (repository/get-all))

(s/defn save [user :- User]
  (repository/save user))

(s/defn edit [id :- s/Str, user :- User]
  (repository/edit id user))

(s/defn delete [id :- s/Str]
  (repository/delete id))

(s/defn get-user-by-id [id :- s/Str]
  (repository/get-user-by-id id))
