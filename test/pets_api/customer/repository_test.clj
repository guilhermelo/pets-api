(ns pets-api.customer.repository_test
  (:require [midje.sweet :refer [facts against-background before fact =>]]
            [pets-api.customer.repository :as repository]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]))

(facts "Operações de CRUD"
       (against-background [(before :facts (repository/limpa-tabela))]

                           (fact "Não deve haver registros"
                                 (count (repository/get-customers)) => 0)

                           (fact "Deve salvar um cliente"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "435345454-X"
                                                 :plan :start
                                                 :monthly-payment 60}]
                                   (repository/save customer)
                                   (count (repository/get-customers)) => 1))

                           (fact "Deve excluir um registro"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "43534545410"
                                                 :plan :medium
                                                 :monthly-payment 60}]
                                   (repository/save customer)
                                   (repository/delete-customer (:id customer))
                                   (count (repository/get-customers)) => 0))))