(ns pets-api.activities.activity
  (:require [schema.core :as s]
            [pets-api.pets.pet :refer [Pet]]))

(s/defschema Activity
  {(s/optional-key :id) s/Str
   :description s/Str
   :date s/Str
   :type (s/enum :vaccine :bath :shear)
   :pet Pet})