(ns dev
  (:require [app.adapter.in.cli :as cli]))

(def paths {:in "dev/input/sample.pdf"
            :out "dev/output/sample.txt"})


(defn dev-run []
  (cli/run (:in paths) (:out paths)))