(ns pets-api.customer.service-test
  (:require [midje.sweet :refer [before facts fact against-background =>]]
            [pets-api.customer.service :as service]
            [pets-api.customer.repository :as repository]))

(facts "Deve retornar clientes"
 (against-background [(before :facts (repository/limpa-tabela))
                      (repository/get-customers) => '()]
                     
                     (fact "Deve retornar lista vazia"
                           (service/get-customers) => '())))

