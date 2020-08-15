(ns pets-api.login.handler
  (:require [compojure.api.sweet :refer [POST]]
            [pets-api.login.login :refer [Login]]
            [pets-api.login.service :as service]
            [schema.core :as s]))

;(defn home [request]
;  (if-not (authenticated? request)
;    (throw-unauthorized)
;    (ok {:status "Logado" :message "Você está logado, hehe"})))

(def login-routes
  [(POST "/" []
     :body [login Login]
     :summary "Login app"
     :return {:token s/Str}
     (service/login login))])
