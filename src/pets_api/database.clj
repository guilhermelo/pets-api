(ns pets-api.database
  (:require [korma.db :as korma]
            ; [toucan.db :as toucan-db]
            ))

(def db-connection-info
  (korma/mysql
   {:classname "com.mysql.cj.jdbc.Driver"
    :subprotocol "mysql"
    :user "guilherme"
    :password "guilherme" 
    :subname "//localhost:3306/petsapi"}))

(korma/defdb db db-connection-info)

; (defn initialize-database []
;   (prn "Iniciando toucan")
;   (toucan-db/set-default-db-connection!
;    {:classname   "org.postgresql.Driver"
;     :subprotocol "postgresql"
;     :subname     "//localhost:5432/petsapi"
;     :user        "guilherme"
;     :password    ""}))


