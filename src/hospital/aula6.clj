(ns hospital.aula6
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn cabe-na-fila? [fila]
  (-> fila
      count
      (< 5)))

(defn chega-em [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila ja esta cheia" {:tentando pessoa}))))

(defn chega-em! [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em (deref fila) pessoa))))

(defn chega-em! [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia []
  (let [hospital {
                  :espera (ref h.model/fila-vazia)
                  :lab1 (ref h.model/fila-vazia)
                  :lab2 (ref h.model/fila-vazia)
                  :lab3 (ref h.model/fila-vazia)}]
    (dosync (chega-em! hospital "guilherme")
            (chega-em! hospital "ana")
            (chega-em! hospital "paula")
            (chega-em! hospital "alef")
            (chega-em! hospital "moreira")
            ;(chega-em! hospital "caio")
            )
    (pprint hospital)
    ))

;(simula-um-dia)

(defn async-chega-em! [hospital pessoa]
  (future (Thread/sleep (rand 5000))
          (dosync
            (println "tentando sync" pessoa)
            (chega-em! hospital pessoa))))

(defn simula-um-dia-async []
  (let [hospital {
                  :espera (ref h.model/fila-vazia)
                  :lab1 (ref h.model/fila-vazia)
                  :lab2 (ref h.model/fila-vazia)
                  :lab3 (ref h.model/fila-vazia)}]
    (async-chega-em! hospital "guilherme")
    ;(async-chega-em! hospital "ana")
    ;(async-chega-em! hospital "paula")
    ;(async-chega-em! hospital "alef")
    ;(async-chega-em! hospital "moreira")
    ;(chega-em! hospital "caio")
    (pprint hospital)
    ))

(simula-um-dia-async)