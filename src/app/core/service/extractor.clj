(ns app.core.service.extractor
  "PDF ファイルを読み込み、各ページを画像に変換し、OCR を用いてテキストを抽出するサービス。"
  (:require [app.core.service.pdf :as pdf]
            [app.core.service.ocr-service :as ocr]
            [clojure.string :as str]))

(defn extract-text
  "PDF を `pdf-path` から読み込み、各ページを OCR 処理してテキストを取得し、
     ページ間を空行で区切りつつ結合した文字列を返す。
  引数:
   - pdf-path: テキスト抽出対象の PDF ファイルのパス
  戻り値:
   - 抽出したテキストを結合した文字列"
  [pdf-path]
  (->> (pdf/pages->images pdf-path)
       (map ocr/image->text)
       (str/join "\n\n")))
