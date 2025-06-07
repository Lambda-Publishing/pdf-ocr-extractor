(ns app.core.service.pdf
  (:require [clojure.java.io :as io])
  (:import  [org.apache.pdfbox.pdmodel     PDDocument]
            [org.apache.pdfbox.rendering   PDFRenderer ImageType]))

(defn pages->images [pdf-path]
  (with-open [doc (PDDocument/load (io/file pdf-path))]
    (let [renderer (PDFRenderer. doc)]
      (mapv #(renderer/renderImageWithDPI % 300 ImageType/GRAY)
            (range (.getNumberOfPages doc))))))
