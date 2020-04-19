(ns pets-api.pets.pet
  (:require [schema.core :as s]
            [pets-api.customer.customer :refer [Customer]]))

(s/defschema Pet
  {(s/optional-key :id) s/Str
   :name s/Str
   :breed s/Str
   :age s/Str
   :owner Customer})