(ns pets-api.fake-db)

(def pets (atom '()))

(defn get-pets []
  @pets)

(defn insere-pet! [pet]
  (let [registro (assoc pet :id "1")]
    (swap! pets conj registro)
    registro))

(defn find-by-id [id]
  (let [pet (filter #(= id (:id %)) (get-pets))]
    (if pet
      (nth pet 0)
      (throw (ex-message "Pet não encontrado")))))

(defn atualiza-pet! [id pet]
  (reset! pets (filter #(not= id (:id %)) (get-pets)))
  (swap! pets conj pet)
  pet)

(defn deleta-pet! [id]
  (let [pet (find-by-id id)]
    (reset! pets (filter #(not= (:id pet) (:id %)) (get-pets)))
    pet))


