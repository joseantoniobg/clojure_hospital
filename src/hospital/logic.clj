(ns hospital.logic)

(defn chega-em
  [hospital departamento paciente]
  (update hospital departamento conj paciente))

(defn atende
  [hospital departamento]
  (get hospital departamento)
  )