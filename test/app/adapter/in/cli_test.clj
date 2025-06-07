(ns app.adapter.in.cli-test
  (:require [clojure.test :refer [deftest is testing]]
            [app.adapter.in.cli :refer [run]]
            [app.core.service.extractor :as extractor]
            [app.adapter.out.writer :as writer]
            [clojure.tools.logging :as log]))

(deftest test-run
  (testing "run function"
    (let [in "test.pdf"
          out "output.txt"
          extracted-text "Sample extracted text"]

      ;; モックの設定
      (with-redefs [extractor/extract-text (fn [_] extracted-text)
                    writer/write-text (fn [_ _] nil)
                    log/infof (fn [& _] nil)
                    log/info (fn [& _] nil)]

        ;; 実行
        (run in out)

        ;; 検証
        (is (= extracted-text (extractor/extract-text in)))
        (is (nil? (writer/write-text extracted-text out)))))))