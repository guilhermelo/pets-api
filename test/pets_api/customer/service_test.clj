  (ns pets-api.customer.service-test
    (:require [midje.sweet :refer [before facts fact against-background =>]]
              [pets-api.customer.service :as service]
              [pets-api.customer.repository :as repository]))

(facts "Deve retornar clientes"
       (against-background [(before :facts (repository/limpa-tabela))
                            (repository/get-all) => '()]

                           (fact "Deve retornar lista vazia"
                                 (service/get-all) => '())

                        ;;    (fact "Deve lançar exceção quando id de delação for inválido"
                        ;;          (try
                        ;;            (def id "")
                        ;;            (service/delete id)
                        ;;            (catch clojure.lang.ExceptionInfo e
                        ;;              (:error e)
                        ;;              (:message e) => "id is invalid")))
                           ))

