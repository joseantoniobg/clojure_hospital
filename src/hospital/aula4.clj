(ns hospital.aula4
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn chega-sem-horrivel! [hospital paciente]
  (swap! hospital h.logic/chega-em :espera paciente)
  (println "após swap " paciente)
  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]]
    (mapv #(.start (Thread. (fn [] (chega-sem-horrivel! hospital %)))) pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]
        starta-chegada #(.start (Thread. (fn [] (chega-sem-horrivel! hospital %))))]
    (mapv starta-chegada pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )

(defn starta-chegada
  [hospital paciente]
  (.start (Thread. (fn [] (chega-sem-horrivel! hospital paciente)))))

(defn prepare
  [hospital]
  (fn [paciente] (starta-chegada hospital paciente))
  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]]
    (mapv (prepare hospital) pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )


(defn starta-chegada
  ([hospital]
   (fn [paciente] (starta-chegada hospital paciente)))
  ([hospital paciente]
   (.start (Thread. (fn [] (chega-sem-horrivel! hospital paciente))))))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]]
    (mapv (starta-chegada hospital) pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]
        starta (starta-chegada hospital)]
    (mapv starta pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )


(defn starta-chegada-partial
  ([hospital paciente]
   (.start (Thread. (fn [] (chega-sem-horrivel! hospital paciente))))))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]
        starta (partial starta-chegada-partial hospital)]
    (mapv starta pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )



(defn starta-chegada-partial
  ([hospital paciente]
   (.start (Thread. (fn [] (chega-sem-horrivel! hospital paciente))))))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555"]]
    (doseq [pessoa pessoas]
      (starta-chegada hospital pessoa))
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (dotimes [pessoa 5]
      (starta-chegada hospital pessoa))
    (.start (Thread. (fn []
                       (Thread/sleep 2000)
                       (pprint hospital)
                       ))))

  )

(simula-um-dia-em-paralelo)