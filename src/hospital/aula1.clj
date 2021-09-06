(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn simula-um-dia []
  (def hospital (h.model/novo-hospital))
  (def hospital (h.logic/chega-em hospital :espera "111"))
  (def hospital (h.logic/chega-em hospital :espera "222"))
  (def hospital (h.logic/chega-em hospital :espera "333"))
  (pprint hospital)

  (def hospital (h.logic/chega-em hospital :lab1 "444"))
  (def hospital (h.logic/chega-em hospital :lab3 "555"))
  (pprint hospital)

  (def hospital (h.logic/atende hospital :lab1))
  (def hospital (h.logic/atende hospital :espera))
  (pprint hospital)

  (def hospital (h.logic/chega-em hospital :espera "444"))
  (def hospital (h.logic/chega-em hospital :espera "255522"))
  (def hospital (h.logic/chega-em hospital :espera "66666"))
  (def hospital (h.logic/chega-em hospital :espera "57865768"))
  (def hospital (h.logic/chega-em hospital :espera "890790"))
  (def hospital (h.logic/chega-em hospital :espera "46968"))
  (pprint hospital)

  )

(defn chega-em-horrivel [paciente]
  (def hospital (h.logic/chega-em-dormente hospital :espera paciente))
  (println "inserindo " paciente)
  )

(defn simula-um-dia-em-paralelo
  []
  (def hospital (h.model/novo-hospital))
    (.start (Thread. (fn [] (chega-em-horrivel "111"))))
    (.start (Thread. (fn [] (chega-em-horrivel "222"))))
    (.start (Thread. (fn [] (chega-em-horrivel "333"))))
    (.start (Thread. (fn [] (chega-em-horrivel "444"))))
    (.start (Thread. (fn [] (chega-em-horrivel "555"))))
    (.start (Thread. (fn [] (chega-em-horrivel "666"))))
    (.start (Thread. (fn []
                             (Thread/sleep 6000)
                             (pprint hospital)
                             )))

  )

; (simula-um-dia)

(simula-um-dia-em-paralelo)