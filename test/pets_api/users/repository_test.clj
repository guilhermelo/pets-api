  (ns pets-api.users.repository_test
    (:require [midje.sweet :refer [facts against-background fact before =>]]
              [pets-api.users.repository :as repository]
              [pets-api.util.uuid.uuid-generator :refer [generate-uuid]]))

(facts "Operações de CRUD"
       (against-background [(before :facts [(repository/limpa-tabela)])]

                           (fact "Não deve haver registro"
                                 (count (repository/get-all)) => 0)

                           (fact "Deve inserir um registro"
                                 (repository/save {:id (generate-uuid)
                                                   :name "João"
                                                   :email "joao@gmail.com"
                                                   :username "joao"
                                                   :password "joao123"})
                                 (count (repository/get-all)) => 1)

                           (fact "Deve editar um registro"
                                 (let [joao {:id (generate-uuid)
                                             :name "João"
                                             :email "joao@gmail.com"
                                             :username "joao"
                                             :password "joao123"}
                                       joao-nome-completo (assoc joao :name "João da Silva")]
                                   (repository/save joao)
                                   (repository/edit (:id joao-nome-completo) joao-nome-completo)
                                   (:name (repository/get-user-by-id (:id joao))) => "João da Silva"))

                           (fact "Deve excluir um registro"
                                 (let [user {:id (generate-uuid)
                                             :name "João"
                                             :email "joao@gmail.com"
                                             :username "joao"
                                             :password "joao123"}]
                                   (repository/save user)
                                   (repository/delete (:id user)) => 1))

                           (fact "Deve recuperar usuário por username"
                                 (let [jose {:id (generate-uuid)
                                             :name "José"
                                             :email "jose@gmail.com"
                                             :username "jose"
                                             :password "jose123"}]
                                   (repository/save jose)
                                   (:email (repository/get-user-by-username "jose")) => "jose@gmail.com"))

                           (fact "Deve recuperar usuário por id"
                                 (let [jose {:id (generate-uuid)
                                             :name "José"
                                             :email "jose@gmail.com"
                                             :username "jose"
                                             :password "jose123"}]
                                   (repository/save jose)
                                   (:name (repository/get-user-by-id (:id jose))) => "José"))))