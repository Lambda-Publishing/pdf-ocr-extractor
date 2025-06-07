(ns app.adapter.in.cli
  "CLI 処理本体。PDF からテキストを抽出して書き出す。"
  (:require [app.core.service.extractor :as extractor]
            [app.adapter.out.writer :as writer]
            [clojure.tools.logging :as log]))

(defn run
  "Extract text from IN (PDF path) and write it to OUT (text path)."
  [in out]
  (log/infof "Extracting text from %s -> %s" in out)
  (-> in
      extractor/extract-text
      (writer/write-text out))
  (log/info "Finished."))
