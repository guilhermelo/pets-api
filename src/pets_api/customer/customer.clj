(ns pets-api.customer.customer
  (:require [schema.core :as s]))

(s/defschema Customer
  {(s/optional-key :id) s/Str
   :name s/Str
   :address s/Str
   :document  s/Str
   :plan [(s/enum :start :medium :premium)]
   :dues s/Int})
