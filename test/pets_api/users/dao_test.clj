(ns pets-api.users.dao_test
  (:require [midje.sweet :refer :all]
            [pets-api.users.dao :as dao]
            [pets-api.util.uuid.uuid-generator :refer :all]))

(facts "Deve salvar um usuário"
       (against-background [(before :facts [(dao/limpa-tabela)])]

                           (fact "Não deve haver registro"
                                 (count (dao/get-users)) => 0)

                           (fact "Deve inserir um registro"
                                 (dao/insert-user {:id (generate-uuid)
                                                   :name "Camila"
                                                   :email "camila123@gmail.com"
                                                   :username "camila"
                                                   :password "camila123"})
                                 (count (dao/get-users)) => 1)))

(facts "Deve excluir um usuário"
       (against-background [(before :facts [(dao/limpa-tabela)])]

                           (fact "Deve excluir um registro"
                                 (let [user {:id (generate-uuid)
                                             :name "Camila"
                                             :email "camila123@gmail.com"
                                             :username "camila"
                                             :password "camila123"}]
                                   (dao/insert-user user)
                                   (dao/delete-user (:id user))
                                   (count (dao/get-users)) => 0))))