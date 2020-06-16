(ns pets-api.login.service
  (:require [pets-api.users.dao :as dao]
            [ring.util.http-response :refer [ok, bad-request]]
            [buddy.sign.jwt :as jwt]
            [java-time :as t]
            [crypto.password.bcrypt :as bcrypt]
            [clojure.string :as s]))

(def secret "86bae26023208e57a5880d5ad644143c567fc57baaf5a942")

(defn- expiration-date []
  (let [date (t/instant)
        local-date (t/plus date (t/hours 1))]
    (t/to-millis-from-epoch local-date)))

(defn- claims [username]
  {:user (keyword username)
   :expiration (expiration-date)})

(defn- generate-token [username]
  (let [claims (claims username)
        token (jwt/sign claims secret {:alg :hs256})]
    token))

(defn- is-jwt-valid? [bearer-token]
  (let [token-parts (s/split bearer-token #" ")
        type (get token-parts 0)
        token (get token-parts 1)]
    (if (= type "Bearer")
      (try
        (let [json-web-token (jwt/unsign token secret)
              expiration (:expiration json-web-token)
              expiration-date (t/local-date-time (t/instant expiration) (t/zone-id)) 
              now-date (t/local-date-time)]
          (if (t/after? now-date expiration-date)
            false
            true))
        (catch Exception e
          (prn e)
          false))
      false)))

(defn authenticated? [request]
  (let [token (get-in request [:headers "authorization"])
        is-token-valid (is-jwt-valid? token)]
    (and token is-token-valid)))

(defn login [user]
  (let [username (:username user)
        password (:password user)
        user (dao/get-user-by-username username)]
    (if (and password user)
      (if (bcrypt/check password (:password user))
        (ok {:token (generate-token username)})
        (bad-request {:message "Senha inválida"}))
      (bad-request {:message "Dados inválidos"}))))