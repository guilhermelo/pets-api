(ns pets-api.pets.repository-test
  (:require [midje.sweet :refer [facts against-background fact before =>]]
            [pets-api.pets.repository :as repository]
            [pets-api.customer.repository :as customer-repository]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]))

(facts "Operações de CRUD"
       (against-background [(before :facts [(repository/limpa-tabela)
                                            (customer-repository/limpa-tabela)])]

                           (fact "Não deve haver registros"
                                 (count (repository/get-all)) => 0)

                           (fact "Deve inserir um registro"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "435345454-X"
                                                 :plan :start
                                                 :monthly-payment 60}]
                                   (customer-repository/save customer)
                                   (repository/insert-pet {:id (generate-uuid)
                                                           :name "Tunico"
                                                           :race "Shih Tzu"
                                                           :age "1"
                                                           :owner (:id customer)}))
                                 (count (repository/get-all)) => 1)))