(ns pets-api.database
  (:require [korma.db :as korma]))

(def db-connection-info
  (korma/mysql
    {:classname "com.mysql.cj.jdbc.Driver"
     :subprotocol "mysql"
     :user "guilherme"
     :password "guilherme"
     :subname "//localhost:3306/petsapi"}))

(korma/defdb db db-connection-info)
