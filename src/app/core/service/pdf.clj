(ns app.core.service.pdf
  (:require [clojure.java.io :as io])
  (:import (org.apache.pdfbox Loader)                         ;; ← NEW
           (org.apache.pdfbox.rendering PDFRenderer ImageType)
           (org.apache.pdfbox.io IOUtils)))


(defn pages->images
  "Return a vector of BufferedImages (300 DPI, grayscale) for every
   page in the PDF at `pdf-path`.  Uses a *temp-file-only* scratch cache,
   which keeps memory usage low for large PDFs."
  [pdf-path]
  (with-open [doc (Loader/loadPDF                                   ;; ← NEW
                   (io/file pdf-path)
                   (IOUtils/createTempFileOnlyStreamCache))]       ;; ← NEW
    (let [renderer (PDFRenderer. doc)]
      (mapv #(.renderImageWithDPI renderer % 300 ImageType/GRAY)
            (range (.getNumberOfPages doc))))))