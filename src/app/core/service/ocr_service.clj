(ns app.core.service.ocr-service
  (:require [clojure.java.io :as io])
  (:import  [net.sourceforge.tess4j
             Tesseract 
             Word
             ITessAPI$TessOcrEngineMode
             ITessAPI$TessPageIteratorLevel]))


(defn tessdata-path 
  "classpath に載っている `resources/tessdata` の実ファイルパスを取る"
  ^String [] 
  (some-> (io/resource "resource/tessdata") .getPath))

(defn tesseract
  ([] (tesseract "jpn"))
  ([lang]
   (doto (Tesseract.)
     (.setDatapath (tessdata-path))   ;; ★ ここを修正
     (.setLanguage lang)
     (.setPageSegMode 3)              ;;DPI を 300→400 
     (.setOcrEngineMode ITessAPI$TessOcrEngineMode/OEM_LSTM_ONLY))))

(defn image->text [^java.awt.image.BufferedImage img]
  (.doOCR (tesseract) img))

(defn image->paragraphs
  "与えられた画像を段落単位で OCR し、
   [{:text \"…\" :bounds java.awt.Rectangle}, …] のベクタで返す。"
  [^java.awt.image.BufferedImage img]
  (let [^Tesseract tess (tesseract)]
    (mapv (fn [^Word w]
            {:text   (.getText w)
             :bounds (.getBoundingBox w)})
          (.getWords tess img ITessAPI$TessPageIteratorLevel/RIL_PARA))))