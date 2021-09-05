(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

; espera
; laboratório

(let [meu-hospital (h.model/novo-hospital)]
  (pprint meu-hospital))