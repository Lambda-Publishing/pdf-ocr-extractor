(ns app.core.service.ocr-service
  (:import [net.sourceforge.tess4j Tesseract]))

(defn tesseract
  ([]     ^Tesseract (tesseract "eng+jpn"))        ; 英語+日本語
  ([lang] (doto (Tesseract.)
            ;; 環境に合わせて datapath を調整
            (.setDatapath "tessdata")
            (.setLanguage lang))))

(defn image->text [^java.awt.image.BufferedImage img]
  (.doOCR (tesseract) img))