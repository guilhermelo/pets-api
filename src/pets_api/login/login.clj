(ns pets-api.login.login
  (:require [schema.core :as s]))

(s/defschema Login
  {:username s/Str
   :password s/Str})
