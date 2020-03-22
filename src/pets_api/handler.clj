(ns pets-api.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [pets-api.pets :refer :all]
            [cheshire.core :as json]
            [pets-api.dao.pets :as dao]))

(defn response [conteudo & [status]]
  {:status       (or status 200)
   :headers      {"Content-Type" "application/json; charset=utf-8"}
   :body         conteudo})


(def app
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

             (GET "/pets" []
                  (ok {:result (dao/get-todos)}))

             (POST "/pets" request
                   (ok {:result (dao/insere (:body request))}))

             (PUT "/pets/:id" request
                  (ok
                    (let [id (get-in request [:params :id])
                          pet-encontrado (dao/get-by-id id)
                          pet-atualizado (:body request)]
                      (if pet-encontrado
                        (do
                          (dao/atualiza id pet-atualizado)
                          {:result pet-atualizado})
                        {:message "Pet não encontrado"}))))

             (DELETE "/pets/:id" [id]
                     (ok (let [pet (dao/get-by-id id)]
                                 (if pet
                                   (do
                                     (dao/deleta id)
                                     {:result pet})
                                   {:message "Pet não encontrado"})))))))
