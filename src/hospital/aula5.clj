(ns hospital.aula5
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn chega-em! [hospital paciente]
  (swap! hospital h.logic/chega-em :espera paciente))

(defn transfere! [hospital de para]
  (swap! hospital h.logic/transfere de para))

(defn simula-um-dia
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (chega-em! hospital "maria")
    (chega-em! hospital "joao")
    (chega-em! hospital "guilherme")
    (transfere! hospital :espera :lab1)
    (pprint hospital)))

(simula-um-dia)
