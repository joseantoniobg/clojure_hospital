(ns hospital.logic)

(defn cabe-na-fila? [hospital departamento]
      (-> hospital
      (get ,,, departamento)
      count ,,,
      (< ,,, 5)))

(defn chega-em
  [hospital departamento paciente]
    (if (cabe-na-fila? hospital departamento)
      (update hospital departamento conj paciente)
      (throw (ex-info "fila ja esta cheia" { :tentando-adicionar paciente }))))

(defn chega-em-dormente
  [hospital departamento paciente]
  (println "Tentando adicionar o paciente" paciente)
  (if (cabe-na-fila? hospital departamento)
    (do
      ; (Thread/sleep (* (rand) 1000))
        (println "Adicionado o paciente" paciente)
        (update hospital departamento conj paciente))
    (throw (ex-info "fila ja esta cheia" { :tentando-adicionar paciente })))

  )

(defn atende
  [hospital departamento]
  (update hospital departamento pop))