# pdf-ocr-extractor

Clojure 製のシンプルな CLI ツール  
**📄 画像 PDF → 📝 プレーンテキスト** へ変換します（Tesseract OCR 使用）。

![Clojure](https://img.shields.io/badge/clojure-1.11.1-blue?logo=Clojure)
![License](https://img.shields.io/github/license/your-name/pdf-ocr-extractor)
<!-- 任意：CI/CD バッジやリリースバッジも追加 -->

---

## ✨ 特長

- **Ports & Adapters** アーキテクチャ採用  
  アダプタ差し替えで CLI / Web / バッチなど拡張しやすい
- **deps.edn だけ** でビルド不要、`clojure -M:run` 即実行
- **PDFBox + Tess4J** 組み合わせで PDF→画像→OCR をワンストップ処理
- 英語・日本語両対応（Tesseract の言語データを追加すれば他言語も）

---

## 📁 ディレクトリ構成

├── adapter/     ; 入出力アダプタ (CLI, ファイル書き込み)
├── core/        ; ユースケース & ドメインロジック
├── ports/       ; 抽象ポート (protocol)
├── sample/      ; ↓ローカル動作確認用
│   ├── input/   ;   PDF を置く
│   └── output/  ;   生成される .txt/.json
├── resources/
├── deps.edn
└── README.md

---

## 🚀 クイックスタート

```bash
# 依存取得（初回のみ）
clojure -P

# サンプル PDF で試す
clojure -M:run sample/input/example.pdf sample/output/example.txt
cat sample/output/example.txt
```

### 動作要件

|ソフト | バージョン例 | インストール例 |
| --- | --- | --- | 
| JDK | 17+ | brew install openjdk |
| Tesseract OCR | 5.x | brew install tesseract (mac) / apt install tesseract-ocr (linux) |
| 言語データ | eng, jpn | TESSDATA_PREFIX=/usr/share/tesseract-ocr/5/tessdata など|

Windows の場合は Tesseract の PATH と tessdata の場所を環境変数に合わせてください。

## 🔧 使い方

``` zsh
# 基本形
clojure -M:run <入力PDF> <出力テキスト>

# JSON 形式で出力したい（予定機能）
clojure -M:run --format json <入力PDF> <出力json>
```

## 🧪 テスト

``` zsh
clojure -X:test
```

## 📦 Docker で実行（オプション）

``` zsh
docker build -t pdf-ocr-extractor .
docker run --rm -v $(pwd)/sample:/work/sample pdf-ocr-extractor \
  sample/input/example.pdf sample/output/example.txt
```

## 🏗️ アーキテクチャ

- core/ 配下にユースケース (extractor.clj) とサービス層
- ports/ に protocol を定義し、外部依存を抽象化
- adapter/in … CLI などエントリポイント

adapter/out … ファイル書き込み・将来は S3 なども可

## 🤝 コントリビュート

1. Issue または Discussion で提案
2. Fork → Feature Branch → PR
3. CI でテストが通ればマージ

## 🪪 ライセンス

[MIT]()

## 📚 参考

- Apache PDFBox
- Tesseract OCR