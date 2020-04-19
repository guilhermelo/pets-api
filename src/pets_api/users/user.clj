(ns pets-api.users.user
  (:require [schema.core :as s]))

(s/defschema User
  {(s/optional-key :id) s/Str
   :name s/Str
   :email s/Str
   :username s/Str
   :password s/Str})
