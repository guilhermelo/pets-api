(ns pets-api.login.middleware.auth-middleware
  (:require [ring.util.http-response :refer [unauthorized]]
            [pets-api.login.service :as service]))

(defn token-auth [handler]
  (fn [request]
    (if (service/authenticated? request)
      (handler request)
      (unauthorized {:message "Não autorizado"}))))