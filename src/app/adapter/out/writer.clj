(ns app.adapter.out.writer
  (:require [clojure.java.io :as io]))

(defn write-text [path text]
  (spit (io/file path) text))