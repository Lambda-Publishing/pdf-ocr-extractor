(ns app.core.service.extractor
  (:require [core.service.pdf         :as pdf]
            [core.service.ocr-service :as ocr]
            [clojure.string           :as str]))

(defn extract-text [pdf-path]
  (->> (pdf/pages->images pdf-path)
       (map ocr/image->text)
       (str/join "\n\n")))
