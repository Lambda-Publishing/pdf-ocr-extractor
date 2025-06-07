(ns app.core.service.extractor-test
  (:require [clojure.test :refer [deftest is testing]]
            [app.core.service.extractor :refer [extract-text]]
            [app.core.service.pdf         :as pdf]
            [app.core.service.ocr-service :as ocr]))

;; 正常系：2ページ分の画像を OCR 処理して「\n\n」結合する
(deftest extract-text-normal-case
  (testing "PDF の各ページを OCR して改行2つで連結する"
    (let [pdf-path "dummy.pdf"
          images   [:img1 :img2]
          text-map {:img1 "text1" :img2 "text2"}]
      (with-redefs [pdf/pages->images (fn [path]
                                        (is (= pdf-path path))
                                        images)
                    ocr/image->text (fn [img]
                                      (get text-map img))]
        (is (= "text1\n\ntext2"
               (extract-text pdf-path))))))

  ;; 空ケース：ページがない場合は空文字列
  (deftest extract-text-empty-case
    (testing "ページが0件なら空文字を返す"
      (with-redefs [pdf/pages->images (constantly [])
                    ocr/image->text   (fn [_]
                                        (throw (Exception. "呼び出されるべきではない")))]
        (is (= "" (extract-text "dummy.pdf")))))))