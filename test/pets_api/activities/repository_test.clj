(ns pets-api.activities.repository-test
  (:require [midje.sweet :refer [facts against-background before fact =>]]
            [pets-api.activities.repository :as repository]
            [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]
            [java-time :as t]))

(facts "Operações de CRUD" :repository
       (against-background [(before :facts (repository/limpa-tabela))]
                           
                           (fact "Deve retornar atividades"
                                 (count (repository/get-by-pet (generate-uuid))) => 0)

                           (fact "Deve salvar uma atividade"
                                 (let [customer {:id (generate-uuid)
                                                 :name "Guilherme"
                                                 :address "Assis-SP"
                                                 :document  "435345454-X"
                                                 :plan :start
                                                 :monthly-payment 60}
                                       pet {:id (generate-uuid)
                                            :name "Tunico"
                                            :race "Shih Tzu"
                                            :age "1"
                                            :owner (:id customer)}
                                       activity {:id (generate-uuid)
                                                 :description "Vacinação contra raiva"
                                                 :date (t/local-date-time)
                                                 :type :vaccine
                                                 :pet pet}]
                                   (repository/save activity)
                                   ;; (count (repository/get-by-pet (:id (:pet activity)))) => 1
                                   ))))