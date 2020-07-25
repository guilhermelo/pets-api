(ns pets-api.customer.dao_test
  (:require [midje.sweet :refer :all]
            [pets-api.customer.dao :as dao]
            [pets-api.customer.customer :refer [Customer]]
            [pets-api.util.uuid.uuid-generator :refer :all]))

(facts "Deve adicionar um cliente"
       (against-background [(before :facts (dao/limpa-tabela))]

                           (fact "Não deve haver registros"
                                 (count (dao/get-customers)) => 0)

                           (fact "Deve salvar um cliente"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "435345454-X"
                                                 :plan :start
                                                 :monthly-payment 60}]
                                   (dao/save customer)
                                   (count (dao/get-customers)) => 1))))

(facts "Deve excluir um cliente"
       (against-background [(before :facts (dao/limpa-tabela))]
                           (fact "Deve excluir um registro"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "43534545410"
                                                 :plan :medium
                                                 :monthly-payment 60}]
                                   (dao/save customer)
                                   (prn (:id customer))
                                   (dao/delete-customer (:id customer))
                                   (count (dao/get-customers)) => 0))))