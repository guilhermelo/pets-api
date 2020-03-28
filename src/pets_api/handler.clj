(ns pets-api.handler
  (:require [compojure.api.sweet :refer :all]
            [metrics.ring.instrument :refer [instrument]]
            [ring.util.http-response :refer :all]
            [pets-api.pets.handler :refer :all]
            [pets-api.users.handler :refer :all]))


(def app
  (instrument
    (api
      {:swagger
       {:ui "/"
        :spec "/swagger.json"
        :data {:info {:title "Pets API"
                      :description "Pets API for derivery data for apps and single page applications"}
               :tags [{:name "PetsAPI", :description "APIs"}]
               }}}

      (context "/api" []
        :tags ["api"]
        (apply routes (concat pet-routes user-routes))))))
