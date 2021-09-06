(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))
(defn test-atom []
  (let [hospital-silveira (atom {:espera h.model/fila-vazia})]
    (println hospital-silveira)
    (pprint hospital-silveira)
    (pprint (deref hospital-silveira))
    (pprint @hospital-silveira)
    (pprint (assoc @hospital-silveira :laboratorio1 h.model/fila-vazia))
    (pprint @hospital-silveira)

    (swap! hospital-silveira assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)

    (update @hospital-silveira :laboratorio1 conj "111")
    (swap! hospital-silveira update :laboratorio1 conj "111")
    (pprint @hospital-silveira)
    ))

; (test-atom)


(defn chega-em-horrivel! [hospital paciente]
  (swap! hospital h.logic/chega-em-dormente :espera paciente)
  ; (def hospital (h.logic/chega-em-dormente hospital :espera paciente))
  (println "após swap " paciente)
  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-horrivel! hospital "666"))))
    (.start (Thread. (fn []
                       (Thread/sleep 6000)
                       (pprint hospital)
                       ))))

  )

; (simula-um-dia)

(simula-um-dia-em-paralelo)