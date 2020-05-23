(ns pets-api.util.uuid.uuid-generator)

(defn generate-uuid []
  (str (java.util.UUID/randomUUID)))
